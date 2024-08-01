package com.example.ProjectOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


//	@Bean
//	CommandLineRunner runner(RunRepository runRepository){
//		return args -> {
//			Run run = new Run(1, "First Run", LocalDateTime.now(),
//                    LocalDateTime.now().plusHours(1),
//					13, Location.OUTDOOR);
//			log.info("{}", run);
//
////			runRepository.create(run);
//		};
//	}
}
