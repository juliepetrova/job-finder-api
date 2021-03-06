package com.webapp.ui.service.base;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.Status;

import java.util.List;

public interface JobApplicationService {
    JobApplication saveJobApplication(JobApplication jobApplication);

    JobApplication findJobApplicationById(int jobApplicationId);

    List<JobApplication> findJobApplicationsByApplicant(int userId);

    List<JobApplication> findJobApplicationByJobId(int jobId);

    List<JobApplication> findJobApplicationsByStatus(Status status);

    List<JobApplication> findByApplicantIdAndStatus(int userId, Status status);

    void changeStatus(int applicationId, int statusId);

    void delete(int applicationId);
}
