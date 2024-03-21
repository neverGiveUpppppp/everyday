package batch.first.job;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class JobEx1_Singular_Step {

    public final JobBuilderFactory jobBuilderFactory;   // JobBuilderFactory' is deprecated since version 5.0.0 and marked for removal
    public final StepBuilderFactory stepBuilderFactory; // StepBuilderFactory' is deprecated since version 5.0.0 and marked for removal

    public ExampleJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job exampleJob(){
        Job exampleJob = jobBuilderFactory.get("exampleJob") // JobBuilder를 통해 'exampleJob'이라는 이름의 Job을 생성
                .start(step())
                .build();
        return exampleJob;
    }

    @Bean
    public Step step(){
        return stepBuilderFactory.get("step")  // StepBuilder를 통해 'step'이라는 이름의 Step을 생성
                .tasklet((contribution, chunkContext) -> { // Tasklet은 Step 내에서 단일 작업을 수행. 여기서는 로그를 출력하는 간단한 Tasklet을 정의
                    log.info("Step!");
                    return RepeatStatus.FINISHED; // Tasklet이 성공적으로 완료
                })
                .build(); // StepBuilder를 통해 Step 인스턴스 생성
    }


}

// 출처 : https://khj93.tistory.com/entry/Spring-Batch%EB%9E%80-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
