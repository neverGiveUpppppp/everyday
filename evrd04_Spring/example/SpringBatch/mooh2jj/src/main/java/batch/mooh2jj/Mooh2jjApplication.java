package batch.mooh2jj;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Mooh2jjApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mooh2jjApplication.class, args);
	}

}
