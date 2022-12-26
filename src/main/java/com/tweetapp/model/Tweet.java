package com.tweetapp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.List;


//@Document(collection = "tweetTable")
@DynamoDBTable(tableName = "tweetTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

   // @Id
   @DynamoDBHashKey(attributeName = "tweetId")
   @DynamoDBAutoGeneratedKey
    private String tweetId;
    @DynamoDBAttribute
    private String userName;

    @DynamoDBAttribute
    @NotBlank
    @Size(min=1,max=144,message="Tweet should not beyond 144 characters")
    private String tweets;
   // @DynamoDBDocument
   @DynamoDBAttribute
    private Tagging tag;
    @DynamoDBAttribute
    private List<Like>likedby ;
    @DynamoDBAttribute
    private List<Reply> replypost;
    @DynamoDBAttribute
    private Date postedat;

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTweets() {
        return tweets;
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public Tagging getTag() {
        return tag;
    }

    public void setTag(Tagging tag) {
        this.tag = tag;
    }


    public List<Like> getLikedby() {
        return likedby;
    }

    public void setLikedby(List<Like> likedby) {
        this.likedby = likedby;
    }

    public List<Reply> getReplypost() {
        return replypost;
    }

    public void setReplypost(List<Reply> replypost) {
        this.replypost = replypost;
    }

    public Date getPostedat() {
        return postedat;
    }

    public void setPostedat(Date postedat) {
        this.postedat = postedat;
    }
}
