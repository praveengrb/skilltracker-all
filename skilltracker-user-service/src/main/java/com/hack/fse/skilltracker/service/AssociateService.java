package com.hack.fse.skilltracker.service;


import com.hack.fse.skilltracker.dao.CandidateProfileDAO;

import com.hack.fse.skilltracker.exceptionhandler.InEligibleForUpdateException;
import com.hack.fse.skilltracker.exceptionhandler.InValidUserException;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.Skill;
import com.hack.fse.skilltracker.util.SkillsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssociateService {
    /**
     *
     */
    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    CandidateProfileDAO candidateProfileDAO;

    @Autowired
    Session producerSession;

    @Autowired
    MessageProducer producer;

    /**
     * @param candidateProfile
     * @return
     */
    public String addProfile(CandidateProfile candidateProfile) throws JMSException {
        LOG.debug("Inside add profile method ");
        candidateProfile.setUserId(UUID.randomUUID().toString());
        producer.send(producerSession.createObjectMessage((Serializable) candidateProfile));
        //CandidateProfile result = candidateProfileDAO.addProfile(candidateProfile);
        LOG.debug("Result" + candidateProfile.toString());
        return candidateProfile.getUserId();
    }

    /**
     * @param candidateid
     * @param skill
     * @return
     * @throws InValidUserException
     */
    public String updateProfile(String candidateid, CandidateProfile candidateProfile) throws InValidUserException, InEligibleForUpdateException {
        CandidateProfile result = candidateProfileDAO.updateCandidateProfile(candidateid,candidateProfile);
        return result.getUserId();
    }
}
