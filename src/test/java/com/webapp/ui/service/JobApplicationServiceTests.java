package com.webapp.ui.service;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.Status;
import com.webapp.ui.repository.JobApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationServiceTests {

    @Mock
    JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    JobApplicationServiceImpl jobApplicationService;

    @Test
    public void testFindJobApplicationById(){
        // Arrange
        JobApplication jobAppl = createJobApplication();
        // Act
        when(jobApplicationRepository.findById(1)).thenReturn(jobAppl);
        JobApplication testJobApplication = jobApplicationService.findJobApplicationById(1);
        // Assert
        assertEquals("01.11.2020", testJobApplication.getDate());
        assertEquals("description", testJobApplication.getDescription());
    }

    @Test
    public void testFindJobApplicationByWrongId(){
        // Arrange
        JobApplication jobApplication = null;
        // Act
        when(jobApplicationRepository.findById(1)).thenReturn(jobApplication);
        // Assert
        assertNull(jobApplicationService.findJobApplicationById(1));
    }

    @Test
    public void testFindJobApplicationsByApplicant(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
        when(jobApplicationRepository.findByApplicantId(1)).thenReturn(jobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationsByApplicant(1);
        // Assert
        assertEquals(new ArrayList<>(), testJobApplications);
        assertEquals((new ArrayList<>()).size(), testJobApplications.size());
    }

    @Test
    public void testFindJobApplicationsByNullApplicant(){
        when(jobApplicationRepository.findByApplicantId(1)).thenReturn(null);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationsByApplicant(1);
        assertNull(testJobApplications);
    }

    @Test
    public void testFindJobApplicationsByJob(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
        when(jobApplicationRepository.findJobApplicationByJobId(1)).thenReturn(jobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationByJobId(1);
        // Assert
        assertEquals(new ArrayList<>(), testJobApplications);
        assertEquals((new ArrayList<>()).size(), testJobApplications.size());
    }

    @Test
    public void testFindJobApplicationsByWrongJobId(){
        when(jobApplicationRepository.findJobApplicationByJobId(1)).thenReturn(null);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationByJobId(1);
        assertNull(testJobApplications);
    }

    @Test
    public void testFindJobApplicationsByStatus(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
//        when(jobApplicationRepository.findByStatus(new Status())).thenReturn(jobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationsByStatus(new Status());
        // Assert
        assertEquals(new ArrayList<>(), testJobApplications);
        assertEquals((new ArrayList<>()).size(), testJobApplications.size());
    }

    @Test
    public void testFindJobApplicationsByNullStatus(){
//        when(jobApplicationRepository.findByStatus(new Status())).thenReturn(null);
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationsByStatus(new Status());
        assertEquals(new ArrayList<>(), testJobApplications);
    }

    @Test
    public void testFindJobApplicationsByApplicantIdAndStatus(){
        // Arrange
        List<JobApplication> jobApplications = new ArrayList<>();
        // Act
//        when(jobApplicationRepository.findByApplicantIdAndStatus(1, new Status())).thenReturn(jobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.findByApplicantIdAndStatus(1, new Status());
        // Assert
        assertEquals(new ArrayList<>(), testJobApplications);
        assertEquals((new ArrayList<>()).size(), testJobApplications.size());
    }

    @Test
    public void testFindJobApplicationsByNullApplicantIdOrStatus(){
//        when(jobApplicationRepository.findByApplicantIdAndStatus(1, new Status())).thenReturn(null);
        List<JobApplication> testJobApplications = jobApplicationService.findByApplicantIdAndStatus(1, new Status());
        assertEquals(new ArrayList<>(), testJobApplications);
    }

    @Test
    public void testSaveJobApplication(){
        // Arrange
        JobApplication jobApplication = createJobApplication();
        // Act
        when(jobApplicationRepository.save(jobApplication)).thenReturn(jobApplication);
        JobApplication tested = jobApplicationService.saveJobApplication(jobApplication);
        // Assert
        assertEquals("01.11.2020", tested.getDate());
        assertEquals("description", tested.getDescription());
    }

    @Test (expected = NullPointerException.class)
    public void testSaveNullJobApplication(){
        JobApplication jobApplication = new JobApplication();
        jobApplicationService.saveJobApplication(jobApplication);
    }

    public JobApplication createJobApplication(){
        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(new Applicant());
        jobApplication.setDate("01.11.2020");
        jobApplication.setDescription("description");
        jobApplication.setJob(new Job());
        jobApplication.setStatus(new Status());
        return jobApplication;
    }
}
