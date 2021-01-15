package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobApplicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    // Get completed applications
    @GetMapping (path = "/pastApplications/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JobApplication> getPastJobApplications(@PathVariable int userId){
        List<JobApplication> applications = new ArrayList<>();
        for (JobApplication appl:
                jobApplicationService.findJobApplicationsByApplicant(userId)) {
            if(appl.getStatus().getId() == 2){
                applications.add(appl);
            }
        }
         return applications;
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

    @GetMapping (path = "/statistics/{userId}")
        public List<JobApplication> getStatistics(@PathVariable int userId){
        Status status = new Status(2, "Completed");
        return jobApplicationService.findByApplicantIdAndStatus(userId, status);
    }


    @PutMapping (path = "/updateStatus/{applicationId}/{statusId}")
    public void changeStatus(@PathVariable int applicationId, @PathVariable int statusId){
        jobApplicationService.changeStatus(applicationId,statusId);
    }


    @DeleteMapping(path = "/{applicationId}")
    public void deleteApplication(@PathVariable int applicationId) {
        jobApplicationService.delete(applicationId);
    }
}

