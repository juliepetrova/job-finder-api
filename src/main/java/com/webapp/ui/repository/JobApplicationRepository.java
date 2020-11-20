package com.webapp.ui.repository;

import com.webapp.ui.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    JobApplication findById(int id);
    JobApplication save(JobApplication jobApplication);

    List<JobApplication> findByApplicantId(int userId);
    List<JobApplication> findJobApplicationByJobId(int jobId);
    void deleteById(int applicationId);
}
