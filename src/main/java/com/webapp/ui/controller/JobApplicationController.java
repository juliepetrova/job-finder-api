package com.webapp.ui.controller;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.service.base.JobApplicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

//    Create applications
    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.saveJobApplication(jobApplication);
    }

//    Get application by ID
    @GetMapping (path = "/{application_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public JobApplication getJobApplicationById(@PathVariable int applicationId) throws NotFoundException {
        JobApplication jobApplication = jobApplicationService.findJobApplicationById(applicationId);
        if(jobApplication != null) {
            return jobApplication;
        }else {
            throw new NotFoundException("Job application not found!");
        }
    }

//    Get applications by applicant
    @GetMapping (path = "/applicant/{user_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JobApplication> getJobApplications (@PathVariable int userId){
        return jobApplicationService.findJobApplicationsByApplicant(userId);
    }
}

