package com.webapp.ui.repository;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int id);
    List<Job> findByUser(int id);
    List<Job> findAll();
    Page<Job> findByStatus(Status status, Pageable pageable);
    Page<Job> findByCity(String city, Pageable pageable);


    void deleteById(int id);

}
