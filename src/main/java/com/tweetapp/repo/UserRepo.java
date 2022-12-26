package com.tweetapp.repo;

import com.tweetapp.model.UserProfile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@EnableScan
public interface UserRepo  extends CrudRepository<UserProfile, String> {
    //save




    @Override
    Optional<UserProfile> findById(String str);

    //@Query("{ 'loginid' : { $regex: ?0 }}")
   // List<UserProfile> findByLoginId(String name);
}
