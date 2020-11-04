package com.webapp.ui.service;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.repository.JobApplicationRepository;
import com.webapp.ui.service.base.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Override
    public JobApplication saveJobApplication(JobApplication jobApplication) {
        if(jobApplication == null || jobApplication.getJob() == null){
            throw new NullPointerException();
        }
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public JobApplication findJobApplicationById(int jobApplicationId) {
        return jobApplicationRepository.findById(jobApplicationId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByApplicant(int userId) {
        return jobApplicationRepository.findByApplicantId(userId);
    }
}
