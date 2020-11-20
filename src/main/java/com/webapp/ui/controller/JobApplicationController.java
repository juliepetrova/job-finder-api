package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.User;
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
    @GetMapping (path = "/{applicationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public JobApplication getJobApplicationById(@PathVariable int applicationId) throws NotFoundException {
        JobApplication jobApplication = jobApplicationService.findJobApplicationById(applicationId);
        if(jobApplication != null) {
            return jobApplication;
        }else {
            throw new NotFoundException("Job application not found!");
        }
    }

//    Get applications by applicant
    @GetMapping (path = "/applicant/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JobApplication> getJobApplications (@PathVariable int userId){
        return jobApplicationService.findJobApplicationsByApplicant(userId);
    }

//    Get applications by job id
    @GetMapping (path = "/job/{jobId}")
    public List<JobApplication> getApplicationsByJobId(@PathVariable int jobId){
        return jobApplicationService.findJobApplicationByJobId(jobId);
    }

//    Get userId by application id
    @GetMapping (path = "/user/{applicationId}")
        public String getUserIdByApplicationId(@PathVariable int applicationId){
        JobApplication jobApplication = jobApplicationService.findJobApplicationById(applicationId);
        return String.valueOf(jobApplication.getApplicant().getId());
    }

    @PutMapping (path = "/updateStatus/{applicationId}/{status}")
    public void changeStatus(@PathVariable int applicationId, @PathVariable int status){
        JobApplication jobApplication = jobApplicationService.findJobApplicationById(applicationId);
        jobApplication.setStatus_id(status);
        jobApplicationService.saveJobApplication(jobApplication);
    }

    @DeleteMapping(path = "/{applicantId}")
    public void deleteApplication(@PathVariable int applicantId) {
        jobApplicationService.delete(applicantId);
    }
}

