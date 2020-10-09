package com.webapp.ui.service.base;

import com.webapp.ui.model.Job;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface JobService {
    List<Job> findAllAvailableJobs();
    Job getJobById(int id);
    List<Job> getJobsByUserId(int user_id);
    List<Job> getJobsByCity(String city);
    Job createJob(Job job);
    void deleteJob(int id);


}
