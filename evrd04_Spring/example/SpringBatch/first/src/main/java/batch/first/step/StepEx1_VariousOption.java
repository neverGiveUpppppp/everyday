package batch.first.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
public class StepEx1_VariousOption {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    public JobEx3_Using_Flow_to_Step(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // Step에서 startlimit사용하기
    @Bean
    @JobScope // 이 빈이 Job의 실행 범위에 속하며, Job 실행 시마다 새로운 인스턴스가 생성되도록 지정
    public Step step_startLimit() throws Exception {
        return stepBuilderFactory.get("Step") // stepBuilderFactory를 사용하여 "Step" 이름으로 Step을 생성
                .startLimit(3)      // 이 Step을 최대 3번까지만 시작할 수 있도록 제한
                .<Member, Member>chunk(10) // 'chunk' 처리 방식 사용 및 한 번에 처리할 아이템의 수를 10으로 지정 //  여기서 Member 타입의 데이터를 read, Member 타입으로 write 한다는 의미
                .reader(reader(null))       // 데이터를 읽는 컴포넌트로, 'reader' 메소드를 호출            // 실제 사용 시에는 null 대신 적절한 인자가 필요함
                .processor(processor(null)) // 읽은 데이터를 처리하는 컴포넌트로, 'processor' 메소드를 호출 // 실제 사용 시에는 null 대신 적절한 인자가 필요함
                .writer(writer(null))       // 처리된 데이터를 쓰는 컴포넌트로, 'writer' 메소드를 호출 //
                .build();
    }


    // Step에서 Skip 사용하기
    @Bean
    @JobScope
    public Step step_skip(){  // skip : 특정 예외를 무시하고 작업을 계속 진행하게 하는 옵션
        return stepBuilderFactory.get("Step")
                .<Member, Member>chunk(10) // chunk 처리 방식 사용 및 한 번에 처리할 아이템 수 지정 // 여기서 Member 타입의 데이터를 읽고, 동일한 타입으로 쓰게 됨
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer(null))
                .faultTolerant() // Step 실행 중 예외가 발생했을 때의 행동을 정의
                .skipLimit(1)    // skip 허용 횟수, 해당 횟수 초과시 Error 발생, Skip 사용시 필수 설정
                .skip(NullPointerException.class)// NullPointerException에 대해선 Skip // NPE 발생 시 스킵하도록 설정. 허용된 스킵 횟수 내에서만 유효함
                .noSkip(SQLException.class) // SQLException에 대해선 noSkip // SQLException 발생 시 스킵하지 않도록 설정. 즉, 이 예외는 반드시 처리해야함을 의미
                //.skipPolicy(new CustomSkipPolilcy) // 사용자가 커스텀하며 Skip Policy 설정 가능
                .build();
    }

    // Step에서 Retry 사용하기
    @Bean
    @JobScope
    public Step step_retry(){   // retry :  특정 예외가 발생 시, 실패 처리하지 않고 정의된 재시도 횟수와 조건에 따라 재시도 하게하는 옵션
        return stepBuilderFactory.get("Step")
                .<Member, Member>chunk(10)
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer(null))
                .faultTolerant()
                .retryLimit(1) //retry 횟수 지정, retry 사용시 필수 설정, 해당 Retry 이후 Exception시 Fail 처리
                .retry(SQLException.class) // SQLException에 대해선 Retry 수행
                .noRetry(NullPointerException.class)    // NullPointerException에 no Retry
//                .retryPolicy(new UserCustomPolicy())  // 사용자정의 Retry Policy 설정 가능
                .build();
    }

    // Step에서 noRollback 사용하기
    @Bean
    @JobScope
    public Step step_no_rollBack(){   // noRollback() : 해당 예외발생 시, 트랜잭션을 롤백하지 않도록 지정
        return stepBuilderFactory.get("Step")
                .<Member, Member>chunk(10)
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer(null))
                .faultTolerant()
                .noRollback(NullPointerException.class) // noRollback() : 해당 예외발생 시, 트랜잭션을 롤백하지 않도록 지정 // 여기서는 NPE 발생 시,  rollback이 되지 않게 설정
                .build();                               // Spring Batch가 해당 예외를 무시하고 작업을 계속 진행할 수 있도록 할 때 필요
    }

    


}
