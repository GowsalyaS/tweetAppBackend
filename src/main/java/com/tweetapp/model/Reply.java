package com.tweetapp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Reply {

    private String replyid;
    private String replies;
    private Date repliedat;
    private Tagging tagged;


    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }


    public Date getRepliedat() {
        return repliedat;
    }

    public void setRepliedat(Date repliedat) {
        this.repliedat = repliedat;
    }

    public Tagging getTagged() {
        return tagged;
    }

    public void setTagged(Tagging tagged) {
        this.tagged = tagged;
    }

}
