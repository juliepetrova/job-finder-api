package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping
    public List<Job> getAvailableJobs() {
        Status status = new Status(1, "Available");
        return jobService.findJobsByStatus(status);
    }

    @GetMapping(path="/{jobId}", produces ={MediaType.APPLICATION_JSON_VALUE})
    public Job getJob(@PathVariable int jobId) throws NotFoundException {
        Job job = jobService.findJobById(jobId);
        if(job != null) {
            return job;
        } else {
            throw new NotFoundException("Job with the specified id not found");
        }
    }

    @GetMapping(path = "/{jobId}/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserByJob(@PathVariable int jobId){
        Job job = jobService.findJobById(jobId);
        return job.getUser();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping
    public Job updateJob(@RequestBody Job job) throws NotFoundException {
        if(jobService.findJobById(job.getId()) != null) {
            return jobService.createJob(job);
        }else{
            throw new NotFoundException("Job not found!");
        }
    }

    @DeleteMapping(path = "/{jobId}")
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }




}
