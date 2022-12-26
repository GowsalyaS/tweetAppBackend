FROM openjdk:11
EXPOSE 8443
ADD target/tweetapp.jar tweetapp.jar
ENTRYPOINT ["java","-jar","/tweetapp.jar"]
