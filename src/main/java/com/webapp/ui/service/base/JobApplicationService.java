package com.webapp.ui.service.base;

import com.webapp.ui.model.JobApplication;

import java.util.Set;

public interface JobApplicationService {
    JobApplication createJobApplication(JobApplication jobApplication);

    JobApplication getJobApplicationById(int jobApplication_id);

    Set<JobApplication> getJobApplicationsByApplicant(int user_id);
}
