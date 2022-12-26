package com.tweetapp;

import com.tweetapp.controller.TweetAppController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages ={"com.tweetapp.service","com.tweetapp.security","com.tweetapp.controller"})
class TweetappApplicationTests {
	@Autowired
	private TweetAppController controller;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

}
