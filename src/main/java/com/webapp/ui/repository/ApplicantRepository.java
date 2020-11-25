package com.webapp.ui.repository;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    Applicant findById(int id);

    Applicant save(Applicant applicant);
}
