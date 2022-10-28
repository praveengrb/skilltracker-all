package com.hack.fse.skilltracker.dao;

import com.hack.fse.skilltracker.exceptionhandler.InEligibleForUpdateException;
import com.hack.fse.skilltracker.exceptionhandler.InValidUserException;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.CandidateProfileDO;

import java.util.List;

public interface CandidateProfileDAO {
    CandidateProfile addProfile(CandidateProfile candidateProfile);

    CandidateProfile updateCandidateProfile(String candidateId,CandidateProfile candidateProfile) throws InValidUserException, InEligibleForUpdateException;

    CandidateProfile getProfileByAssociateId(String candidateName);
    CandidateProfile getProfileByUserId(String userId);

    /**
     * Getter for property 'allProfiles'.
     *
     * @return Value for property 'allProfiles'.
     */
    List<CandidateProfileDO> getAllProfiles();
}
