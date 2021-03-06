package com.webapp.ui.service.base;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;


public interface JobService {
    List<Job> findAllAvailableJobs();
    Job findJobById(int id);
    List<Job> findJobsByUserId(int userId);
    Page<Job> findJobsByCity(String city, Status status, Pageable pageable);
    Page<Job> findJobsByStatus(Status status, Pageable pageable);
    Job createJob(Job job);
    void deleteJob(int id);
    long countAllJobs();
    String getMostPopularCity();
    String getTotalEarnings();


}
