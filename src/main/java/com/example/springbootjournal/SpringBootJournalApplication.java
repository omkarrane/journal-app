package com.example.springbootjournal;

import com.example.springbootjournal.domain.Journal;
import com.example.springbootjournal.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringBootJournalApplication {

	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new Journal("Get to know Spring Boot", new Date("16/04/2020"), "Today I will learn Spring Boot"));
			repo.save(new Journal("Simple Spring Boot Project", new Date("17/04/2020"), "I will do my first Spring Boot Project"));
			repo.save(new Journal("Spring Boot Reading", new Date("17/04/2020"), "Read more about Spring Boot"));
			repo.save(new Journal("Spring Boot in the Cloud", new Date("20/04/2020"), "Spring Boot using Cloud Foundry"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}

}
