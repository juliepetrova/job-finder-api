package com.webapp.ui.repository;

import com.webapp.ui.model.JobReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobReportRepository extends JpaRepository<JobReport, Integer> {

    JobReport findById(int id);
    JobReport save(JobReport jobReport);
    List<JobReport> findAll();
    void deleteById(int id);

}
