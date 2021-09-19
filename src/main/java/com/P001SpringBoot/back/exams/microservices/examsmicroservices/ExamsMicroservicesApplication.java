package com.P001SpringBoot.back.exams.microservices.examsmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.persistence.Entity;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.P001SpringBoot.back.models.entity"})
public class ExamsMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsMicroservicesApplication.class, args);
	}

}
