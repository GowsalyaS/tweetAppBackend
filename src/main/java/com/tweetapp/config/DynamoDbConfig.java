package com.tweetapp.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.tweetapp.repo.UserRepo;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages ="com.tweetapp.repo")
public class DynamoDbConfig {

    @Value("${aws.dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.accessKey}")
    private String dynamodbAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String dynamodbSecretKey;




    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey));
    }

   public AwsClientBuilder.EndpointConfiguration endpointConfiguration(){
        return new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint,awsRegion);
   }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration())
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }
}
