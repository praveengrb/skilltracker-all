package com.hack.fse.skilltracker.service;


import com.hack.fse.skilltracker.dao.CandidateProfileDAO;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.CandidateProfileDO;
import com.hack.fse.skilltracker.util.SkillsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    /**
     * Candidate profile dao object
     */
    @Autowired
    private CandidateProfileDAO candidateProfileDAO;

    /**
     * Get all the candidate profiles
     *
     * @return
     */
    private List<CandidateProfileDO> getAllCandidateProfile() {
        return candidateProfileDAO.getAllProfiles();
    }

    private List<CandidateProfileDO> getBySkillSet(String skillSet) {
        SkillsUtil utils = new SkillsUtil();
        List<CandidateProfileDO> profiles = getAllCandidateProfile();
        List<CandidateProfileDO> result = new ArrayList<CandidateProfileDO>();
        if (utils.isValidNonTechSkill(skillSet.toUpperCase())) {
            profiles.forEach(
                    profile -> {
                        if(null != profile.getNonTechnicalSkills())
                            profile.getNonTechnicalSkills().forEach(nonTechSkill -> {
                                if (nonTechSkill.getSkillName().equalsIgnoreCase(skillSet)
                                        && nonTechSkill.getExpertise() > 10)
                                    result.add(profile);
                            });
                    });
        } else if (utils.isValidTechSkill(skillSet.toUpperCase())) {
            profiles.forEach(profile -> {
                if(null != profile.getTechnicalSkills())
                    profile.getTechnicalSkills().forEach(techSkill -> {
                        if (techSkill.getSkillName().equalsIgnoreCase(skillSet)
                                && techSkill.getExpertise() > 10)
                            result.add(profile);
                    });
            });
            }
        return result;
    }

    private List<CandidateProfileDO> getByContainsCharacter(String nameStarting) {
        List<CandidateProfileDO> profiles = getAllCandidateProfile();
        List<CandidateProfileDO> result = new ArrayList<CandidateProfileDO>();
        profiles.forEach(profile -> {
            if (profile.getName().toLowerCase().contains(nameStarting.toLowerCase())) {
                result.add(profile);
            }
        });
        return result;
    }

    private CandidateProfile getByAssociateID(String associateID) {
        return candidateProfileDAO.getProfileByAssociateId(associateID);
    }

    private CandidateProfile getUserById(String userId) {
        return candidateProfileDAO.getProfileByUserId(userId);
    }

    public ResponseEntity<Object> getProfiles(String criteria, String criteriaValue) {

        switch (criteria.trim().toLowerCase()) {
            case "name":
                return new ResponseEntity<>(getByContainsCharacter(criteriaValue), HttpStatus.OK);
            case "associate_id":
                return new ResponseEntity<>(getByAssociateID(criteriaValue), HttpStatus.OK);
            case "skill":
                return new ResponseEntity<>(getBySkillSet(criteriaValue), HttpStatus.OK);
            case "userid":
                return new ResponseEntity<>(getUserById(criteriaValue), HttpStatus.OK);
            default:
                Map<String, Object> response = new HashMap<>();
                response.put("errors", "Invalid criteria. It should be name/associate_id/skill");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }
}