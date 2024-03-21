package batch.first.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class JobEx3_Using_Flow_to_Step {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    public JobEx3_Using_Flow_to_Step(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    public JobBuilder jobBuilder;
    public SimpleJobBuilder simpleJobBuilder;
    public FlowBuilder flowBuilder;
    public FlowBuilder.TransitionBuilder transitionBuilder;
    public FlowBuilder<JobBuilder> flowBuilder2;


    @Bean
    public Job exampleJob(){
        Job job = jobBuilderFactory.get("exampleJob")
                .start(startStep())
                .on("FAILED") // startStep의 ExitStatus가 FAILED일 경우,
                .to(failOverStep())  // failOver Step 실행
                .on("*")      // failOver Step의 결과와 상관없이(모든)
                .to(writeStep())     // write Step 실행
                .on("*")      // write Step의 결과와 상관없이(모든)
                .end()               // Flow 종료

                .from(startStep())          // startStep이 FAILED이 아니면
                .on("COMPLETED") // COMPLETED일 경우
                .to(processStep())      // process Step 실행
                .on("*")         // process Step의 결과와 상관없이(모든)
                .to(writeStep())        // write Step 실행
                .on("*")         // wrtie Step의 결과와 상관없이(모든)
                .end()                  // Flow 종료

                .from(startStep())      // startStep의 결과가 FAILED, COMPLETED이 아닌
                .on("*")     // 모든 경우
                .to(writeStep())    // write Step 실행
                .on("*")     // write Step의 결과와 상관없이(모든)
                .end()              // Flow 종료

                .end()
                .build();
        return job;
    }

    @Bean
    public Step startStep(){
        TaskletStep taskletStep = stepBuilderFactory.get("startStep")
                    .tasklet((contribution, chunkContext) -> {
                        log.info("Start Step!");

                        String result = "COMPLETE";
    //                    String result = "FAIL";
    //                    String result = "UNKNOWN";

                        // Flow에서 on은 RepeatStatus가 아닌 ExitStatus를 바라본다.
                        if (result.equals("COMPLETED"))
                            contribution.setExitStatus(ExitStatus.COMPLETED);
                        else if (result.equals("FAILED"))
                            contribution.setExitStatus(ExitStatus.FAILED);
                        else if (result.equals("UNKNOWN"))
                            contribution.setExitStatus(ExitStatus.UNKNOWN);
                        return RepeatStatus.FINISHED;
                    })
                    .build();
        return taskletStep;
    }

    @Bean
    public Step failOverStep(){
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Fail Over Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step processStep(){
        return stepBuilderFactory.get("processStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Process Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step writeStep(){
        return stepBuilderFactory.get("writeStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Write Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }



}


// 출처 : https://khj93.tistory.com/entry/Spring-Batch%EB%9E%80-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0