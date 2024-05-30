package kr.co.polycube.backendtest.lottos.config;

import kr.co.polycube.backendtest.lottos.LottoRepository;
import kr.co.polycube.backendtest.lottos.WinnerRepository;
import kr.co.polycube.backendtest.lottos.domain.Lotto;
import kr.co.polycube.backendtest.lottos.domain.Winner;
import kr.co.polycube.backendtest.lottos.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Set;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;
    private final LottoService lottoService;

    @Bean
    public Job checkWinnerJob(JobRepository jobRepository, Step checkWinnerStep) {
        return new JobBuilder("checkWinnerJob", jobRepository)
                .start(checkWinnerStep)
                .build();
    }

    @Bean
    public Step checkWinnerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("checkWinnerStep", jobRepository)
                .tasklet(checkWinnerTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet checkWinnerTasklet() {
        return (contribution, chunkContext) -> {
            List<Lotto> lottos = lottoRepository.findAll();
            Set<Integer> winningNumbers = lottoService.generateWinningNumbers(); // 당첨 번호 생성

            // 로또 번호 중 당첨 번호와 일치하는 개수 계산
            lottos.forEach(lotto -> {
                long matchCount = List.of(
                        lotto.getNumber1(),
                        lotto.getNumber2(),
                        lotto.getNumber3(),
                        lotto.getNumber4(),
                        lotto.getNumber5(),
                        lotto.getNumber6()
                ).stream().filter(winningNumbers::contains).count();

                // 일치 개수에 따른 등수 계산
                int rank = calculateRank(matchCount);
                if (rank > 0) {
                    Winner winner = new Winner();
                    winner.setLottoId(lotto.getId());
                    winner.setRank(rank);
                    winnerRepository.save(winner);
                }
            });
            return RepeatStatus.FINISHED;
        };
    }

    private int calculateRank(long matchCount) {
        return switch ((int) matchCount) {
            case 6 -> 1; // 6개 일치 - 1등
            case 5 -> 2; // 5개 일치 - 2등
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5; // 2개 일치 - 5등
            default -> 0;
        };
    }
}