package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

//    @GetMapping
//    public List<Job> getAvailableJobs() {
//        Status status = new Status(1, "Available");
//        return jobService.findJobsByStatus(status);
//    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getByCity(@RequestParam(required = false) String city,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "12") int size){
        try {
            List<Job> jobs = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<Job> filteredJobs;
            if (city == null) {
                Status status = new Status(1, "Available");
                filteredJobs = jobService.findJobsByStatus(status, paging);
            }
            else {
//                Also add status
                filteredJobs = jobService.findJobsByCity(city, paging);
            }
            jobs = filteredJobs.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("jobs", jobs);
            response.put("currentPage", filteredJobs.getNumber());
            response.put("totalItems", filteredJobs.getTotalElements());
            response.put("totalPages", filteredJobs.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping(path = "/statistics")
    public Map<String, String> getAllJobsStats(){
        Map<String, String> stats = new Hashtable<>();
        stats.put("allJobs", Long.toString(jobService.countAllJobs()));
        stats.put("mostPopularCity", jobService.getMostPopularCity());
        stats.put("totalEarnings", jobService.getTotalEarnings());
        return stats;
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
    @PutMapping (path = "/updateStatus/{jobId}/{statusId}")
    public void changeStatus(@PathVariable int jobId, @PathVariable int statusId){
        Job job = jobService.findJobById(jobId);
        Status status = new Status(statusId, "");
        job.setStatus(status);
        jobService.createJob(job);
    }

    @DeleteMapping(path = "/{jobId}")
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }




}
