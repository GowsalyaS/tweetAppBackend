//package com.tweetapp.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaService {
//   private final  Logger logger = LoggerFactory.getLogger(KafkaService.class);
//    @KafkaListener(topics = "tweetapp",groupId = "tweet-group")
//    public void consume(String msg){
//        logger.info(String.format("msg consumed->%s",msg));
//
//    }
//}
