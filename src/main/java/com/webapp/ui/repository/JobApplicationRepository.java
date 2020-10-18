package com.webapp.ui.repository;

import com.webapp.ui.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    JobApplication findById(int id);
    JobApplication save(JobApplication jobApplication);

    Set<JobApplication> findByApplicantId(int user_id);
}
