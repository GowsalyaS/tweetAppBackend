package com.tweetapp.repo;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserProfile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableScan
@Repository
public interface TweetsRepo extends CrudRepository<Tweet, String> {

    // @Query("{ 'userName' : ?0 }")

//    @Override
//    Optional<Tweet> findById(Integer integer);
   // @Override
    List<Tweet> findByuserName(String name);


}
