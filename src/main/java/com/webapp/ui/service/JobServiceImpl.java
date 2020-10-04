package com.webapp.ui.service;

import com.webapp.ui.model.Job;
import com.webapp.ui.repository.JobRepository;
import com.webapp.ui.repository.UserRepository;
import com.webapp.ui.service.base.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;


    @Override
    public List<Job> getAllAvailableJobs() {
        // Where status is available
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id);
    }


    @Override
    public List<Job> getJobsByUserId(int user_id) {
        return jobRepository.findByUser(user_id);
    }

    @Override
    public List<Job> getJobsByCity(String city) {
        return jobRepository.findByCity(city);
    }


    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return null;
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.deleteById(id);
    }
}
