package com.hack.fse.skilltracker.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.UUID;

public class UUIDConverter implements DynamoDBTypeConverter<String, UUID> {

    @Override
    public String convert(UUID uuid) {
        if(uuid == null){
            return null;
        }
        return uuid.toString();
    }

    @Override
    public UUID unconvert(String s) {
        return UUID.fromString(s);
    }
}
