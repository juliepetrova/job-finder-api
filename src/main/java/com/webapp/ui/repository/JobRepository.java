package com.webapp.ui.repository;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int id);
    List<Job> findByUser(int id);
    List<Job> findAll();
    Page<Job> findByStatus(Status status, Pageable pageable);
    Page<Job> findByCityAndStatus(String city, Status status, Pageable pageable);
    long count();
    void deleteById(int id);

//        Query
    @Query(value = "SELECT city FROM Job j GROUP BY j.city ORDER BY COUNT(id) DESC")
    List<String> getMostPopularCity();

    @Query(value = "SELECT SUM(payment) FROM Job j WHERE status_id = 2")
    double totalEarnings();

}
