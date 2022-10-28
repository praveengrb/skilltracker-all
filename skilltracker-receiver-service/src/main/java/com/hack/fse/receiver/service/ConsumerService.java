package com.hack.fse.receiver.service;

import com.hack.fse.skilltracker.dao.CandidateProfileDAO;
import com.hack.fse.skilltracker.model.CandidateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Value("${spring.activemq.queue.name}")
    String queueName;

    @Autowired
    private CandidateProfileDAO candidateProfileDAO;

    @JmsListener(destination = "${spring.activemq.queue.name}", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(CandidateProfile candidateProfile) {
        System.out.println("Received candidateProfile <" + candidateProfile + ">");
        candidateProfileDAO.addProfile(candidateProfile);
    }

}