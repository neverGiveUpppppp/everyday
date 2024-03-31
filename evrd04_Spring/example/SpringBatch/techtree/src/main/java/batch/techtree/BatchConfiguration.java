package batch.techtree;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing // Spring Batch Job 처리에 필요한 Bean들을 사용한다는 어노테이션 (여기서는 memory-based Database를 사용하고자 선언)
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    } // SpringBatch에서 정의된 ItemReader 객체를 활용해, 데이터를 읽어오는 함수. 읽어온 데이터를 Person 객체로 변환

/*
ItemReader로 읽어온 데이터는 ItemProcessor를 통해 데이터를 변환 및 가공 가능
   위에서 ItemReader를 통해 reader()에서 파일 데이터를 읽어왔고, 이를 PersonItemProcessor클래스에서
   ItemProcessor를 통해 가공할 예정

*/

    @Bean
    public PersonItemProcessor processor(){
        return new PersonItemProcessor();
    } // Spring Batch에 데이터 변환처리를 담당할 클래스라고 알려줌

/*
데이터 변환 및 가공 처리가 끝난 데이터를 
최종적으로 파일로 저장할지, DB에 저장할지, 외부로 전송할지 지정해야함.
    여기서는 DB에 저장
 */

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (first_name, last_name) VALUES(:firstName, :lastname)")
                .dataSource(dataSource)
                .build();
    }


    @Bean
    public Job importUserJob(JobCompletionNotificationListener listner, Step step1){
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listner)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer){
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }


}




// 출처 : https://it-techtree.tistory.com/entry/creating-a-batchservice-using-springboot