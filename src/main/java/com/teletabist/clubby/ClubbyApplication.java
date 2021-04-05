package com.teletabist.clubby;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource("classpath:config/app.yml"),
	@PropertySource("classpath:config/db.yml")
})
@SpringBootApplication
public class ClubbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubbyApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment env){
		return args -> {
			// Logger.getLogger(ClubbyApplication.class).info("Message: "+env.getProperty("spring.datasource.url"));
		};
	}

}
