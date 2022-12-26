package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages ={"com.tweetapp.config","com.tweetapp.util","com.tweetapp.security","com.tweetapp.exception"
,"com.tweetapp.model","com.tweetapp.repo","com.tweetapp.service","com.tweetapp.validation","com.tweetapp.controller"})
//@EnableMongoRepositories("com.tweetapp.repo")
@EnableSwagger2
public class TweetappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}





	}
