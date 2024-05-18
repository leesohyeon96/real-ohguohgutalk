package com.shl.ohguohgutalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories // Spring Data mongoDB 사용하기 위함
public class OhguohgutalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhguohgutalkApplication.class, args);
	}

}
