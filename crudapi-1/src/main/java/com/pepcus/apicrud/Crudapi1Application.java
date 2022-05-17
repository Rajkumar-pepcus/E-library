package com.pepcus.apicrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Crudapi1Application {

	public static void main(String[] args) {
		SpringApplication.run(Crudapi1Application.class, args);
	}

}
