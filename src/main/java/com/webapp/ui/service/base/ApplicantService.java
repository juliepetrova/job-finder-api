package com.webapp.ui.service.base;

import com.webapp.ui.model.Applicant;

public interface ApplicantService {
    Applicant findApplicantById(int id);

    Applicant createApplicant(Applicant applicant);
}
