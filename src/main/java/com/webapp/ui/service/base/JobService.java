package com.webapp.ui.service.base;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;

import java.util.List;


public interface JobService {
    List<Job> findAllAvailableJobs();
    Job findJobById(int id);
    List<Job> findJobsByUserId(int userId);
    List<Job> findJobsByCity(String city);
    List<Job> findJobsByStatus(Status statusId);
    Job createJob(Job job);
    void deleteJob(int id);


}
