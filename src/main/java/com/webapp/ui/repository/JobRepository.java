package com.webapp.ui.repository;

import com.webapp.ui.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int id);
    List<Job> findByUser(int id);
    List<Job> findByCity(String city);
//    @Query("Select * from job j left join status s on j.status_id = s.id") //This is using a named query method
    List<Job> findAll();

    void deleteById(int id);

}
