package com.webapp.ui.service;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.repository.ApplicantRepository;
import com.webapp.ui.service.base.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;


    @Override
    public Applicant findApplicantById(int id) {
        return applicantRepository.findById(id);
    }

    @Override
    public Applicant createApplicant(Applicant applicant) {
        if(applicant == null || applicant.getId() == 0){
            throw new NullPointerException();
        }
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant) {
        if(applicant == null || applicant.getId() == 0){
            throw new NullPointerException();
        }
        return applicantRepository.save(applicant);
    }
}
