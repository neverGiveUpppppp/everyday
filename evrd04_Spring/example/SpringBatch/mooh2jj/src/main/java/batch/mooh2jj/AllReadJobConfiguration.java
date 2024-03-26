package batch.mooh2jj;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/*
Cursor 기반 ItemReader 구현체
    JdbcCursorItemReader
    HibernateCursorItemReader
    StoredProcedureItemReader

Paging 기반 ItemReader 구현체
    JdbcPagingItemReader
    HibernatePagingItemReader
    JpaPagingItemReader
 */

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AllReadJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private static final int CHUNK_SIZE = 4;
    private static final int FETCH_SIZE = 4;

    @Bean
    public Job allReadJob(Step allReadStep){
        return jobBuilderFactory.get("allReadJob")
                .start(allReadStep)
                .build();
    }

    @Bean
    public Step allReadStep(ItemReader<Schedule> allReadPagingReader, ItemWriter<Schedule> allReadWriter){
        return stepBuilderFactory.get("allReadStep")
                .<Schedule, Schedule>chunk(CHUNK_SIZE)
                .reader(allReadPagingReader)
                .writer(allReadWriter)
                .allowStartIfComplete(true)
                .build();
    }


    // Cursor 기반 ItemReader 구현체
    @Bean
    public JdbcCursorItemReader<Schedule> allReadReader(){
        return new JdbcCursorItemReader<Schedule>()
                .setVerifyCursorPosition(false)
                .fetchSize(FETCH_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Schedule.class))
                .sql("select * from schedules order by id")
                .name("jdbcCursorItemReader")
                .build();
    }

    // Paging 기반 ItemReader 구현체
    @Bean
    public JdbcPagingItemReader<Schedule> allReadPagingReader(PagingQueryProvider queryProvider){
        return new JdbcPagingItemReader<Schedule>()
                .pageSize(CHUNK_SIZE)
                .fetchSize(FETCH_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Schedule.class))
                .queryProvider(queryProvider)
                .name("jdbcCursorItemReader")
                .build();
                
    }

    @Bean
    public PagingQueryProvider queryProvider() throws Exception{
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("*");
        queryProvider.setFromClause("from schedules");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }

    @Bean
    public ItemWriter<Schedule> allReadWriter(){
        return list -> log.info("write items. \n"
                + list.stream().map(s -> s.toString())
                .collect(Collectors.joining("\n")));
    }


}
