package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.User;
import com.webapp.ui.repository.JobRepository;
import com.webapp.ui.service.base.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;
//    @Autowired
//    JobRepository jobRepository;


    @GetMapping
    public List<Job> getAvailableJobs() {
        return jobService.findAllAvailableJobs();
    }

    @GetMapping(path="/{jobId}", produces ={MediaType.APPLICATION_JSON_VALUE})
    public Job getJob(@PathVariable int jobId){

        return jobService.getJobById(jobId);
    }

    @GetMapping(path = "/{job_id}/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserByJob(@PathVariable int job_id){
        Job job = jobService.getJobById(job_id);
        return job.getUser();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
//        User user = new User();
//        user.setId(12);
//        user.setFirst_name("fsd"); user.setCity("fsf"); user.setCountry("fsfs"); user.setDate_of_birth("fsf"); user.setEmail("fsf"); user.setLast_name("dss");
//        user.setPhone_number("6789"); user.setUsername("gfsdf");
//        Job newJob = new Job();
//        newJob.setAddress("fs");
//        newJob.setCity("ds");
//        newJob.setDate("2020-10-06T15:28:29.666905");
//        newJob.setDescription("dsds");
//        newJob.setHours(32);
//        newJob.setPayment(2323);
//        newJob.setStatus_id(1);
//        newJob.setTitle("fde");
//        newJob.setUser(user);
//        return jobRepository.save(job);
        return jobService.createJob(job);
    }

    @PutMapping
    public Job updateJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping(path = "/{job_id}")
    public void deleteJob(@PathVariable int job_id){
        jobService.deleteJob(job_id);
    }




}
