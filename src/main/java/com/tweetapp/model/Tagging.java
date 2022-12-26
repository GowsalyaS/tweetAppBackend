package com.tweetapp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Tagging {

    private String tagid;

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }
}
