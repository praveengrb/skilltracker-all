package com.hack.fse.skilltracker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@DynamoDBTable(tableName="skilltracker")
public class CandidateProfileDO {
    private String id;
    private String email;
    private String userId;
    private String associateId;
    private String mobile;
    private String name;
    List<Skill> technicalSkills;
    List<Skill> nonTechnicalSkills;



    /**
     * Getter for property 'mobile'.
     *
     * @return Value for property 'mobile'.
     */
    @DynamoDBAttribute(attributeName="mobile")
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter for property 'mobile'.
     *
     * @param mobile Value to set for property 'mobile'.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    @DynamoDBAttribute(attributeName="name")
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }


    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @DynamoDBAttribute(attributeName="userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName="associateid")
    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
    @DynamoDBTypeConverted(converter = SkillsConverter.class)
    @DynamoDBAttribute(attributeName = "technicalskills")
    public List<Skill> getTechnicalSkills(){
        return this.technicalSkills;
    }
    public void setTechnicalSkills(List<Skill> technicalSkills){
        this.technicalSkills=technicalSkills;
    }
    @DynamoDBTypeConverted(converter = SkillsConverter.class)
    @DynamoDBAttribute(attributeName = "nontechnicalskills")
    public List<Skill> getNonTechnicalSkills() {
        return nonTechnicalSkills;
    }

    public void setNonTechnicalSkills(List<Skill> nonTechnicalSkills) {
        this.nonTechnicalSkills = nonTechnicalSkills;
    }
}
