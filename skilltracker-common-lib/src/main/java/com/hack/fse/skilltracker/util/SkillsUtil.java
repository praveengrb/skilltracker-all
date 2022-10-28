package com.hack.fse.skilltracker.util;

import java.util.Arrays;
import java.util.List;

public class SkillsUtil {
    protected static final String[] TECH_SKILLS = {"HTML","CSS","JAVASCRIPT", "ANGULAR", "REACT",
            "SPRING", "RESTFUL", "HIBERNATE", "GIT", "DOCKER", "JENKINS", "AWS"};
    protected static final String[] NON_TECH_SKILLS = {"SPOKEN", "COMMUNICATION", "APTITUDE"};
    List<String> techSkills = Arrays.asList(TECH_SKILLS);
    List<String> nonTechSkills = Arrays.asList(NON_TECH_SKILLS);

    public  boolean isValidTechSkill(String skill) {
        return techSkills.contains(skill);
    }

    public  boolean isValidNonTechSkill(String skill) {
        return nonTechSkills.contains(skill);
    }
}
