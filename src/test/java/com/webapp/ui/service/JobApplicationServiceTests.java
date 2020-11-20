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
        when(jobApplicationRepository.findById(1)).thenReturn(createJobApplication());
        JobApplication testJobApplication = jobApplicationService.findJobApplicationById(1);
        assertEquals("01.11.2020", testJobApplication.getDate());
        assertEquals("description", testJobApplication.getDescription());
    }

    @Test
    public void testFindJobApplicationByWrongId(){
        when(jobApplicationRepository.findById(1)).thenReturn(null);
        assertNull(jobApplicationService.findJobApplicationById(1));
    }

    @Test
    public void testFindJobApplicationsByApplicant(){
        when(jobApplicationRepository.findByApplicantId(1)).thenReturn(new ArrayList<>());
        List<JobApplication> testJobApplications = jobApplicationService.findJobApplicationsByApplicant(1);
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
    public void testSaveJobApplication(){
        JobApplication jobApplication = createJobApplication();
        when(jobApplicationRepository.save(jobApplication)).thenReturn(jobApplication);
        JobApplication tested = jobApplicationService.saveJobApplication(jobApplication);
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
