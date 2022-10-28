package com.hack.fse.skilltracker.controller;

import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.Skill;
import com.hack.fse.skilltracker.service.AssociateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@SpringBootTest
@Profile("h2")
public class SkillTrackerAssociateControllerTest {
    @Autowired
    SkillTrackerAssociateController associateController;
    @Mock
    org.springframework.validation.Errors errors;
    @Mock
    CandidateProfile dummyCandidateProfile;
    @MockBean
    private AssociateService associateService;
    @BeforeEach
    void setUp() throws JMSException {
    	when(associateService.addProfile(Mockito.any())).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    public void testAddAndUpdateCandidateProfile() throws JMSException {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName("Praveen");
        candidateProfile.setMobile("9999999999");
        candidateProfile.setEmail("praveengrb@gmail.com");
        ResponseEntity<Object> result=associateController.addProfile(candidateProfile,errors);

        HashMap map=(HashMap)result.getBody();
        System.out.println("Result"+map);
        Skill nonTechSkill = new Skill();
        nonTechSkill.setExpertise(15);
        nonTechSkill.setSkillName("SPOKEN");

        Skill techSkill = new Skill();
        techSkill.setExpertise(15);
        techSkill.setSkillName("AWS");
        List<Skill> techSkills = new ArrayList<>();
        techSkills.add(techSkill);
        techSkills.add(nonTechSkill);
        //associateController.updatedProfile((String)map.get("user_id"),techSkills,errors);

    }

    @Test
    public void testUpdateProfile() throws JMSException {

        Skill nonTechSkill = new Skill();
        nonTechSkill.setExpertise(15);
        nonTechSkill.setSkillName("SPOKEN");

        Skill techSkill = new Skill();
        techSkill.setExpertise(15);
        techSkill.setSkillName("AWS");
        List<Skill> techSkills = new ArrayList<>();
        techSkills.add(techSkill);
        //techSkills.add(nonTechSkill);
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName("Praveen");
        candidateProfile.setMobile("99");
        candidateProfile.setEmail("praveengrb@");
        candidateProfile.setAssociateId("26612");
      candidateProfile.setTechnicalSkills(techSkills);
      candidateProfile.setId("1");
      candidateProfile.setNonTechnicalSkills(Stream.of(nonTechSkill).collect(Collectors.toList()));
      associateController.addProfile(candidateProfile, errors);
      techSkill.setExpertise(15);
      techSkill.setSkillName("JAVASCRIPT");
      techSkills.add(techSkill);
      candidateProfile.setTechnicalSkills(techSkills);
     // associateController.updatedProfile("1",techSkills,errors);
        associateController.ping();
    }
    @Test
    public void testAddUpdateCandidateSkillProfileWithException() throws JMSException {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName("Praveen");
        candidateProfile.setMobile("99");
        candidateProfile.setEmail("praveengrb@");
        candidateProfile.setAssociateId("26612");
        Errors errors = new org.springframework.validation.BindException(candidateProfile, "candidateProfile");
        ResponseEntity<Object> result=associateController.addProfile(candidateProfile,errors);
    }

    @Test
    public void testAddUpdateCandidateSkillProfile1() throws JMSException {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName("");
        candidateProfile.setMobile("");
        candidateProfile.setEmail("");
        candidateProfile.setAssociateId("");
        Errors errors = new org.springframework.validation.BindException(candidateProfile, "candidateProfile");
        ResponseEntity<Object> result=associateController.addProfile(candidateProfile,errors);
    }

    @Test
    public void testAddCandidateSkillProfile() throws JMSException {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName("Praveen");
        candidateProfile.setMobile("9999999999");
        candidateProfile.setEmail("praveengrb@gmail.com");
        Skill nonTechSkill = new Skill();
        nonTechSkill.setExpertise(15);
        nonTechSkill.setSkillName("SPOKEN");
        List<Skill> nonTechSkills = new ArrayList<>();
       nonTechSkills.add(nonTechSkill);
        candidateProfile.setNonTechnicalSkills(nonTechSkills);
        Skill techSkill = new Skill();
        techSkill.setExpertise(15);
        techSkill.setSkillName("AWS");
        List<Skill> techSkills = new ArrayList<>();
        techSkills.add(techSkill);
        candidateProfile.setTechnicalSkills(techSkills);
        associateController.addProfile(candidateProfile,errors);
    }
//
//    @Test
//    public void testUpdateCandidateProfile(){
//        Skill nonTechSkill = new Skill();
//        nonTechSkill.setExpertise(15);
//        nonTechSkill.setSkillName("SPOKEN");
//        List<Skill> nonTechSkills = new ArrayList<>();
//        nonTechSkills.add(nonTechSkill);
//        candidateProfile.setNonTechnicalSkills(nonTechSkills);
//        Skill techSkill = new Skill();
//        techSkill.setExpertise(15);
//        techSkill.setSkillName("AWS");
//        List<Skill> techSkills = new ArrayList<>();
//        associateController.updatedProfile(null,null,errors);
//    }
}
