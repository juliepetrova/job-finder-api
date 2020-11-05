package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.service.base.JobApplicationService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationControllerTest {

    @Mock
    JobApplicationService jobApplicationService;

    @InjectMocks
    JobApplicationController jobApplicationController;

    @Test
    public void testCreateApplication(){
        JobApplication jobApplication = createJobApplication();
        when(jobApplicationService.saveJobApplication(jobApplication)).thenReturn(jobApplication);
        JobApplication tested = jobApplicationController.createJobApplication(jobApplication);
        assertEquals("01.11.2020", tested.getDate());
        assertEquals("description", tested.getDescription());
    }

    @Test
    public void testGetApplicationById() throws NotFoundException {
        when(jobApplicationService.findJobApplicationById(1)).thenReturn(createJobApplication());
        JobApplication testJobApplication = jobApplicationController.getJobApplicationById(1);
        assertEquals("01.11.2020", testJobApplication.getDate());
        assertEquals("description", testJobApplication.getDescription());
    }

//    @Test (expected = NotFoundException.class)
//    public void testGetApplicationByInvalidId() throws NotFoundException {
//        when(jobApplicationService.findJobApplicationById(1)).thenReturn(null);
//        assertNull(jobApplicationController.getJobApplicationById(1));
//    }

    @Test
    public void testGetApplicationsByApplicantId(){
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getJobApplications(1);
        assertEquals(jobApplications.size(), foundJobApplications.size());

    }

    @Test
    public void testGetApplicationsByApplicantIdWithZeroApplications(){
        List<JobApplication> jobApplications = new ArrayList<>();
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getJobApplications(1);
        assertEquals(jobApplications.size(), foundJobApplications.size());
    }

    public JobApplication createJobApplication(){
        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(new Applicant());
        jobApplication.setDate("01.11.2020");
        jobApplication.setDescription("description");
        jobApplication.setJob(new Job());
        jobApplication.setStatus_id(1);
        return jobApplication;
    }
}
