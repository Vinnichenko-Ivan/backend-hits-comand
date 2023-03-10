package ru.hits.hitsback.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class HitsBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HitsBackApplication.class, args);
	}

}
