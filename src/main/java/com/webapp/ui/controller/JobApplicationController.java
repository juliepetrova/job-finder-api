package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

//    Create applications
    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.createJobApplication(jobApplication);
    }

//    Get application by ID
    @GetMapping (path = "/{application_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public JobApplication getJobApplicationById(@PathVariable int application_id){
        return jobApplicationService.getJobApplicationById(application_id);
    }


    @GetMapping (path = "/applicant/{user_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<JobApplication> getApplications (@PathVariable int user_id){
        return jobApplicationService.getJobApplicationsByApplicant(user_id);
    }
}

