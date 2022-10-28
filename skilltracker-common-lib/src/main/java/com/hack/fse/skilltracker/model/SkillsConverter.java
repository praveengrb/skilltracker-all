package com.hack.fse.skilltracker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class SkillsConverter implements DynamoDBTypeConverter<String, List<Skill>> {

    @Override
    public String convert(List<Skill> objects) {
        //Jackson object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String objectsString = objectMapper.writeValueAsString(objects);
            return objectsString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Skill> unconvert(String objectsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Skill> objects = objectMapper.readValue(objectsString, new TypeReference<List<Skill>>(){});
            return objects;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}