package com.webapp.ui.service;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.repository.ApplicantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantTest {

    @Mock
    ApplicantRepository applicantRepository;

    @InjectMocks
    ApplicantServiceImpl applicantService;

    @Test
    public void testGetApplicantById() {
        when(applicantRepository.findById(12)).thenReturn(createApplicant());
        Applicant testApplicant = applicantService.findApplicantById(12);
        assertEquals(12, testApplicant.getId());
        assertEquals("experience", testApplicant.getExperience());
        assertEquals("picture", testApplicant.getPicture());
        assertEquals(4.5, testApplicant.getRating(), 1);
        assertEquals("skills", testApplicant.getSkills());
    }

    @Test
    public void testGetApplicantByWrongId(){
        when(applicantRepository.findById(12)).thenReturn(null);
        assertNull(applicantService.findApplicantById(12));
    }

    @Test
    public void testSaveApplicant(){
        Applicant applicant = createApplicant();
        when(applicantRepository.save(applicant)).thenReturn(applicant);
        applicantService.createApplicant(applicant);
    }

    @Test(expected = NullPointerException.class)
    public void testSaveNullApplicant(){
        applicantService.createApplicant(new Applicant());
    }


    public Applicant createApplicant() {
        Applicant applicant = new Applicant();
        applicant.setId(12);
        applicant.setExperience("experience");
        applicant.setPicture("picture");
        applicant.setRating(4.5);
        applicant.setSkills("skills");
        return applicant;
    }
}
