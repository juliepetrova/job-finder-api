package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;


    @GetMapping
    public List<Job> getAvailableJobs() {
        return jobService.findAllAvailableJobs();
    }

    @GetMapping(path="/{jobId}", produces ={MediaType.APPLICATION_JSON_VALUE})
    public Job getJob(@PathVariable int jobId){

        return jobService.findJobById(jobId);
    }

    @GetMapping(path = "/{job_id}/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserByJob(@PathVariable int jobId){
        Job job = jobService.findJobById(jobId);
        return job.getUser();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping
    public Job updateJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }



    @DeleteMapping(path = "/{job_id}")
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }




}
