package kr.co.polycube.backendtest.lottos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job checkWinnerJob;

    @Scheduled(cron = "0 0 0 * * SUN")
    public void runCheckWinnerJob() throws Exception {
        jobLauncher.run(checkWinnerJob, new JobParameters());
    }

}