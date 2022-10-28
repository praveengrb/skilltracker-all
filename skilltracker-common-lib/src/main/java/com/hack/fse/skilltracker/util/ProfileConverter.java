package com.hack.fse.skilltracker.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hack.fse.skilltracker.model.Skill;

import java.io.IOException;
import java.util.List;

public class ProfileConverter implements DynamoDBTypeConverter<String, List<Skill>> {

        @Override
        public String convert(List<Skill> objects) {
            //Jackson object mapper
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String objectsString = objectMapper.writeValueAsString(objects);
                return objectsString;
            } catch (JsonProcessingException e) {
                //do something
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
                //do something
            } catch (JsonMappingException e) {
                //do something
            } catch (IOException e) {
                //do something
            }
            return null;
        }
    }

