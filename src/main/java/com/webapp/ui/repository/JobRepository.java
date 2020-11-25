package com.webapp.ui.repository;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int id);
    List<Job> findByUser(int id);
    List<Job> findByCity(String city);
//    @Query("Select * from job j left join status s on j.status_id = s.id") //This is using a named query method
    List<Job> findAll();

//    @Query(value = "FROM Job j WHERE j.status_id = ?1") //This is using a named query method
    List<Job> findByStatus(Status status);

    void deleteById(int id);

}
