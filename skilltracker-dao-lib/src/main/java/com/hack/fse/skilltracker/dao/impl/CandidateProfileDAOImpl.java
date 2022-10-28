package com.hack.fse.skilltracker.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.hack.fse.skilltracker.dao.CandidateProfileDAO;
import com.hack.fse.skilltracker.dao.repository.AssociateRepository;
import com.hack.fse.skilltracker.exceptionhandler.ErrorCodes;
import com.hack.fse.skilltracker.exceptionhandler.InEligibleForUpdateException;
import com.hack.fse.skilltracker.exceptionhandler.InValidUserException;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.CandidateProfileDO;
import com.hack.fse.skilltracker.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateProfileDAOImpl implements CandidateProfileDAO {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB buildAmazonDynamoDB;
    @Autowired
    AssociateRepository associateRepository;

    /** Add Candidate Profile if not exists. */
    @Override
    public CandidateProfile addProfile(CandidateProfile entityToSave) {
        return associateRepository.saveProfile(entityToSave);
//        List<CandidateProfileDO> associatesList = associateRepository.findAll();
//        boolean profileFound =false;
//        List<CandidateProfile> filteredList = associatesList.stream().filter(candidateProfile ->
//        candidateProfile.getAssociateId().equals(entityToSave.getAssociateId())).collect(Collectors.toList());
        //CandidateProfile candidateProfile = associateRepository.findProfileByAssociateID(entityToSave.getAssociateId());
//        return (filteredList.isEmpty())?
//                associateRepository.saveProfile(entityToSave)
//                :null;
    }
    
    @Override
    public CandidateProfile getProfileByUserId(String userId) {
        List<CandidateProfileDO> associatesList = associateRepository.findAll();
        boolean profileFound =false;
        List<CandidateProfileDO> filteredList = associatesList.stream().filter(candidateProfile ->
                candidateProfile.getUserId().equals(userId)).collect(Collectors.toList());
        //CandidateProfile candidateProfile = associateRepository.findProfileByAssociateID(entityToSave.getAssociateId());
        if(filteredList.isEmpty()){
            return null;
        }else{
            CandidateProfileDO profiles = filteredList.get(0);
            CandidateProfile candidateProfile= new CandidateProfile();
            candidateProfile.setId(profiles.getUserId());
            candidateProfile.setEmail(profiles.getEmail());
            candidateProfile.setUserId(profiles.getUserId());
            candidateProfile.setAssociateId(profiles.getAssociateId());
            candidateProfile.setMobile(profiles.getMobile());
            candidateProfile.setTechnicalSkills(profiles.getTechnicalSkills());
            candidateProfile.setNonTechnicalSkills(profiles.getNonTechnicalSkills());
            candidateProfile.setName(profiles.getName());
            return candidateProfile;
        }
    }

    /** {@inheritDoc} */
    @Override
    public CandidateProfile updateCandidateProfile(String candidateId, CandidateProfile candidateProfile) throws InValidUserException, InEligibleForUpdateException {
        List<CandidateProfileDO> associatesList = associateRepository.findAll();
        boolean profileFound =false;
        List<CandidateProfileDO> filteredList = associatesList.stream().filter(candidateProfile1 ->
                candidateProfile1.getId().equals(candidateId)).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new InValidUserException(ErrorCodes.UID_100);
        }
        //isEligibleForUpdate( candidateProfileDb.get().getModified());
        return associateRepository.saveProfile(candidateProfile);
    }
    /**
     * Profile must be allowed to update after 10 days of last change.
     */
    private boolean isEligibleForUpdate(LocalDateTime  lastUpdated) throws InEligibleForUpdateException {
        boolean eligibility = false;
        LocalDateTime currentDate= LocalDateTime.now();
        long diff = Duration.between(currentDate,lastUpdated).getSeconds();
        long tendays = 864000000;
        if (diff > tendays) {
            eligibility = true;
        } else{
            throw new InEligibleForUpdateException(ErrorCodes.IE_001);
        }

        return eligibility;

    }
    /** {@inheritDoc}
     * @return*/
    @Override
    public CandidateProfile getProfileByAssociateId(String associateID) {
        return associateRepository.findProfileByAssociateID(associateID);
    }

    /** {@inheritDoc} */
    public List<CandidateProfileDO> getAllProfiles() {
        return associateRepository.findAll();
    }
}
