package com.webapp.ui.service;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.repository.JobRepository;
import com.webapp.ui.service.base.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;


    @Override
    public List<Job> findAllAvailableJobs() {
        // Where status is available
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(int id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> findJobsByUserId(int userId) {
        return jobRepository.findByUser(userId);
    }

//    TODO
    public List<Job> findCurrentJobsByUserId(){return null;}
    public List<Job> findCompletedJobsBYUser(){return null;}

    @Override
    public List<Job> findJobsByCity(String city) {
        return jobRepository.findByCity(city);
    }

    @Override
    public List<Job> findJobsByStatus(Status statusId) {
        return jobRepository.findByStatus(statusId);
    }

    @Override
    public Job createJob(Job job) {
        if(job == null || job.getTitle() == null){
            throw new NullPointerException();
        }
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.deleteById(id);
    }
}
