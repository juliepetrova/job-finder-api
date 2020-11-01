package com.webapp.ui.service;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.repository.JobApplicationRepository;
import com.webapp.ui.service.base.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public JobApplication findJobApplicationById(int jobApplication_id) {
        return jobApplicationRepository.findById(jobApplication_id);
    }

    @Override
    public List<JobApplication> findJobApplicationsByApplicant(int user_id) {
        return jobApplicationRepository.findByApplicantId(user_id);
    }
}
