package com.hack.fse.skilltracker.dao.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.CandidateProfileDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Repository("associateRepository")
public class AssociateRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB buildAmazonDynamoDB;

    public PaginatedScanList<CandidateProfileDO> findAll(){
        return dynamoDBMapper.scan(CandidateProfileDO.class, new DynamoDBScanExpression());
    }
    public PaginatedScanList<CandidateProfileDO> findAll1(){
        return dynamoDBMapper.scan(CandidateProfileDO.class, new DynamoDBScanExpression());
    }
    public CandidateProfile updateProfile(CandidateProfileDO profiles) {
        CandidateProfile candidateProfile= new CandidateProfile();
        candidateProfile.setId(profiles.getUserId());
        candidateProfile.setEmail(profiles.getEmail());
        candidateProfile.setUserId(profiles.getUserId());
        candidateProfile.setAssociateId(profiles.getAssociateId());
        candidateProfile.setMobile(profiles.getMobile());
        candidateProfile.setTechnicalSkills(profiles.getTechnicalSkills());
        candidateProfile.setName(profiles.getName());
        try {
            dynamoDBMapper.save(profiles);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return candidateProfile;
    }
    public CandidateProfile saveProfile(CandidateProfile candidateProfile) {

        CandidateProfileDO profiles=new CandidateProfileDO();
        profiles.setId(candidateProfile.getUserId());
        profiles.setEmail(candidateProfile.getEmail());
        profiles.setUserId(candidateProfile.getUserId());
        profiles.setAssociateId(candidateProfile.getAssociateId());
        profiles.setMobile(candidateProfile.getMobile());
        profiles.setTechnicalSkills(candidateProfile.getTechnicalSkills());
        profiles.setNonTechnicalSkills(candidateProfile.getNonTechnicalSkills());
        profiles.setName(candidateProfile.getName());
        try {
            dynamoDBMapper.save(profiles);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return candidateProfile;
    }

    public CandidateProfile findProfileByUserId(String userId) {
        return dynamoDBMapper.load(CandidateProfile.class, userId);
    }

    public CandidateProfile findProfileByAssociateID(String associateId){
        return dynamoDBMapper.load(CandidateProfile.class, associateId);
    }
    public CandidateProfile findProfileByCandidateId(String candidateId){
        return dynamoDBMapper.load(CandidateProfile.class, candidateId);
    }
}
