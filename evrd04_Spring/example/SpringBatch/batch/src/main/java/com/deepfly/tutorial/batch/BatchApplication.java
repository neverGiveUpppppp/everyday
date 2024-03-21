package com.deepfly.tutorial.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}

// 출처 : https://deeplify.dev/back-end/spring/batch-tutorial#%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B0%B0%EC%B9%98%EA%B0%80-%EC%A0%9C%EA%B3%B5%ED%95%A0-%EC%88%98-%EC%9E%88%EB%8A%94-%EB%B9%84%EC%A6%88%EB%8B%88%EC%8A%A4-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4
