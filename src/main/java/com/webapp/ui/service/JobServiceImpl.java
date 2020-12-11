package com.webapp.ui.service;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.repository.JobRepository;
import com.webapp.ui.service.base.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Job> findJobsByCity(String city, Status status, Pageable pageable) {
        return jobRepository.findByCityAndStatus(city,status, pageable);
    }

    @Override
    public Page<Job> findJobsByStatus(Status statusId, Pageable pageable) {
        return jobRepository.findByStatus(statusId, pageable);
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

    @Override
    public long countAllJobs() {
        return jobRepository.count();
    }

    @Override
    public String getMostPopularCity() {
        List<String> stats = jobRepository.getMostPopularCity();
        return stats.get(0);
    }

    @Override
    public String getTotalEarnings() {
        int earnings = (int)jobRepository.totalEarnings();
        return Integer.toString(earnings);
    }
}
