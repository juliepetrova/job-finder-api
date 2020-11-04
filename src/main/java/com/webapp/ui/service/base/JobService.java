package com.webapp.ui.service.base;

import com.webapp.ui.model.Job;
import java.util.List;


public interface JobService {
    List<Job> findAllAvailableJobs();
    Job findJobById(int id);
    List<Job> findJobsByUserId(int userId);
    List<Job> findJobsByCity(String city);
    Job createJob(Job job);
    void deleteJob(int id);


}
