package com.webapp.ui.service.base;

import com.webapp.ui.model.Job;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface JobService {
    List<Job> findAllAvailableJobs();
    Job findJobById(int id);
    List<Job> findJobsByUserId(int user_id);
    List<Job> findJobsByCity(String city);
    Job createJob(Job job);
    void deleteJob(int id);


}
