package com.hack.fse.skilltracker.controller;


import com.hack.fse.skilltracker.exceptionhandler.ErrorHandler;
import com.hack.fse.skilltracker.exceptionhandler.InEligibleForUpdateException;
import com.hack.fse.skilltracker.exceptionhandler.InValidUserException;
import com.hack.fse.skilltracker.model.CandidateProfile;
import com.hack.fse.skilltracker.model.Skill;
import com.hack.fse.skilltracker.service.AssociateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RefreshScope
public class SkillTrackerAssociateController {
    private static final Logger LOG = LoggerFactory.getLogger(SkillTrackerAssociateController.class.getName());

    @Autowired
    private ErrorHandler errorHandler;
    @Autowired
    private AssociateService associateService;
    @Value("${ping.message}")
    String pingMessage;

    private static final String BASE_PATH="/skill-tracker/api/v1/engineer";
    private static final String ADD_PROFILE_PATH=BASE_PATH+"/add-profile";
    private static final String UPDATE_PROFILE_PATH = BASE_PATH+"/update-profile/{userId}";
    private static final int BAD_REQUEST=400;


    @GetMapping("/ping")
    public String ping(){
        return pingMessage;
    }

    @ApiOperation(value="addProfile",notes="User can add their profile by providing personal information.")
    @ApiResponse(code = BAD_REQUEST, message = "Exceptions based on incorrect input")
    @PostMapping(ADD_PROFILE_PATH)
    public ResponseEntity<Object> addProfile(@Valid @RequestBody CandidateProfile candidateProfile, Errors errors) throws JMSException {
        ResponseEntity<Object> responseEntity;
        List<String> errorMessages=validateCandidateProfile(candidateProfile,errors);
        if(errorMessages.isEmpty()){
            LOG.debug("CandidateProfile's Information " + candidateProfile.toString());
            Map<String,Object> response=new HashMap<>();
            response.put("user_id", associateService.addProfile(candidateProfile));
            responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
        } else{
            responseEntity=new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @ApiOperation(value="updateProfile",notes= "User can update their profile by providing user id. " +
            "While updating their profile, They are allowed to update only expertise level of their skill")
    @ApiResponse(code = BAD_REQUEST, message = "Status")
    @PutMapping(UPDATE_PROFILE_PATH)
    public ResponseEntity<Object> updatedProfile(@PathVariable(value = "userId") String userId,
    												@Valid @RequestBody CandidateProfile candidateProfile, Errors errors) {
        ResponseEntity<Object> responseEntity;
        List<String> errorMessages=new ArrayList<>();
        if(errors.hasErrors()){
            errorMessages=errorHandler.handleArgumentException(errors);
        }

        try {
            LOG.debug("CandidateProfile's Information " + candidateProfile.toString());
            String status= associateService.updateProfile(candidateProfile.getId(), candidateProfile);
            Map<String,Object> response=new HashMap<>();
            response.put("user_id", status);
            responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
        } catch (InValidUserException e) {
            e.printStackTrace();
            errorMessages.add(e.getMessage());
            responseEntity= new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }catch (InEligibleForUpdateException e) {
            e.printStackTrace();
            errorMessages.add(e.getMessage());
            responseEntity= new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
    List<String> validateCandidateProfile(CandidateProfile candidateProfile, Errors errors){
        List<String> errorMessages=new ArrayList<>();
        if(errors.hasErrors()){
            errorMessages=errorHandler.handleArgumentException(errors);
        }
        if (candidateProfile.getAssociateId()!=null&&!candidateProfile.getAssociateId().startsWith("CTS")) {
            errorMessages.add("CandidateProfile ID should start with prefix 'CTS'");
        }
        return errorMessages;
    }
}
