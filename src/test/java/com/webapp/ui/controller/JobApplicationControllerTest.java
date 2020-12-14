package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.Status;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationControllerTest {

    @Mock
    JobApplicationService jobApplicationService;

    @InjectMocks
    JobApplicationController jobApplicationController;

    @Test
    public void testCreateApplication(){
        // Arrange
        JobApplication jobApplication = createJobApplication();
        // Act
        when(jobApplicationService.saveJobApplication(jobApplication)).thenReturn(jobApplication);
        JobApplication tested = jobApplicationController.createJobApplication(jobApplication);
        // Assert
        assertEquals("01.11.2020", tested.getDate());
        assertEquals("description", tested.getDescription());
    }

    @Test
    public void testGetApplicationById() throws NotFoundException {
        // Arrange
        JobApplication jobApplication = createJobApplication();
        // Act
        when(jobApplicationService.findJobApplicationById(1)).thenReturn(jobApplication);
        JobApplication testJobApplication = jobApplicationController.getJobApplicationById(1);
        // Assert
        assertEquals("01.11.2020", testJobApplication.getDate());
        assertEquals("description", testJobApplication.getDescription());
    }

    @Test (expected = NotFoundException.class)
    public void testGetApplicationByInvalidId() throws NotFoundException {
        when(jobApplicationService.findJobApplicationById(1)).thenReturn(null);
        assertNull(jobApplicationController.getJobApplicationById(1));
    }

    @Test
    public void testGetApplicationsByApplicantId(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        // Act
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getJobApplications(1);
        // Assert
        assertEquals(jobApplications.size(), foundJobApplications.size());

    }

    @Test
    public void testGetApplicationsByApplicantIdWithZeroApplications(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getJobApplications(1);
        // Assert
        assertEquals(jobApplications.size(), foundJobApplications.size());
    }

    @Test
    public void testGetPastApplications() {
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        Status st = new Status(2, "Past");
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        JobApplication jobApplication = createJobApplication();
        jobApplication.setStatus(st);
        jobApplications.add(jobApplication);
        // Act
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getPastJobApplications(1);
        // Assert
        assertEquals(1, foundJobApplications.size());
    }

    @Test
    public void testGetPastApplicationsWhenNone() {
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        // Act
        when(jobApplicationService.findJobApplicationsByApplicant(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getPastJobApplications(1);
        // Assert
        assertEquals(0, foundJobApplications.size());
    }

    @Test
    public void testGetApplicationsByJobId() {
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        jobApplications.add(createJobApplication());
        // Act
        when(jobApplicationService.findJobApplicationByJobId(1)).thenReturn(jobApplications);
        List<JobApplication> foundJobApplications = jobApplicationController.getApplicationsByJobId(1);
        // Assert
        assertEquals(4, foundJobApplications.size());
    }

    @Test
    public void testGetUserIdByApplicationId() {
        // Arrange
        JobApplication jobApplication = createJobApplication();
        Applicant applicant = new Applicant();
        applicant.setId(3);
        jobApplication.setApplicant(applicant);
        // Act
        when(jobApplicationService.findJobApplicationById(1)).thenReturn(jobApplication);
        String id = jobApplicationController.getUserIdByApplicationId(1);
        // Assert
        assertEquals("3", id);
    }

    @Test
    public void testGetStatistics() {
        // Arrange
        Status status = new Status(3, "Completed");
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
        List<JobApplication> foundJobApplications = jobApplicationController.getStatistics(1);
        // Assert
        assertEquals(0, foundJobApplications.size());
    }

    @Test
    public void testDeleteJobApplication(){
        // Arrange
        int applicationId = 1;
        // Act
        jobApplicationController.deleteApplication(applicationId);
    }

    public JobApplication createJobApplication(){
        Status st = new Status(1, "Available");
        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(new Applicant());
        jobApplication.setDate("01.11.2020");
        jobApplication.setDescription("description");
        jobApplication.setJob(new Job());
        jobApplication.setStatus(st);
        return jobApplication;
    }
}
