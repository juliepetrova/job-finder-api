package com.webapp.ui.service;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.repository.JobApplicationRepository;
import com.webapp.ui.service.base.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Override
    public JobApplication createJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public JobApplication getJobApplicationById(int jobApplication_id) {
        return jobApplicationRepository.findById(jobApplication_id);
    }

    @Override
    public Set<JobApplication> getJobApplicationsByApplicant(int user_id) {
        return jobApplicationRepository.findByApplicantId(user_id);
    }
}