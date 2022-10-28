package com.hack.fse.skilltracker.controller;

import com.hack.fse.skilltracker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Value("${ping.message}")
    String pingMessage;
    private static final String BASE_PATH = "/skill-tracker/api/v1/admin/search/{criteria}/{criteriaValue}";
    @Autowired
    private AdminService adminService;
   @GetMapping(BASE_PATH)
    public ResponseEntity<Object> getCandidates(@PathVariable(value = "criteria") String criteria,
                                                @PathVariable(value = "criteriaValue") String criteriaValue) {
        return adminService.getProfiles(criteria, criteriaValue);

    }
    @GetMapping("/skill-tracker/api/v1/admin/searchuser/{userId}")
    public ResponseEntity<Object> getCandidateProfile(@PathVariable(value = "userId") String userId) {
        return adminService.getProfiles("userid",userId);

    }
    @GetMapping("/ping")
    public String ping(){
        return pingMessage;
    }
}

