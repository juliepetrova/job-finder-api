package com.webapp.ui.repository;

import com.webapp.ui.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int id);
    List<Job> findByUser(int id);
    List<Job> findByCity(String city);
}
