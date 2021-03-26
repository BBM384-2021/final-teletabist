package com.teletabist.clubby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

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

}
