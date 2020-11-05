package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerTest {
    
    @Mock
    JobService jobService;
    
    @InjectMocks
    JobController jobController;
    
    @Test 
    public void testGetJobs(){
        List<Job> jobs = new ArrayList<>();
        jobs.add(createJob());
        jobs.add(createJob());
        jobs.add(createJob());
        jobs.add(createJob());
        when(jobService.findAllAvailableJobs()).thenReturn(jobs);
        List<Job> testJobs = jobController.getAvailableJobs();
        assertEquals(jobs.size(), testJobs.size());
    }
    
    @Test
    public void testGetJobsNoRecords(){
        List<Job> jobs = new ArrayList<>();
        when(jobService.findAllAvailableJobs()).thenReturn(jobs);
        List<Job> testJobs = jobController.getAvailableJobs();
        assertEquals(0, testJobs.size());
    }
    
    @Test 
    public void testGetJobById() throws NotFoundException {
        Job job = createJob();
        when(jobService.findJobById(1)).thenReturn(job);
        Job testJob = jobController.getJob(1);
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals(1, testJob.getStatus_id());
        assertEquals("title", testJob.getTitle());
    }
    
    @Test (expected = NotFoundException.class)
    public void testGetJobByInvalidId() throws NotFoundException {
        when(jobService.findJobById(1)).thenReturn(null);
        jobController.getJob(1);
    }
    
    @Test
    public void testGetUserByJob(){
        Job job = createJob();
        User u = new User();
        job.setUser(u);
        when(jobService.findJobById(1)).thenReturn(job);
        User user = jobController.getUserByJob(1);
        assertEquals(u, user);

    }
    
    @Test
    public void testCreateJob(){
        Job job = createJob();
        when(jobService.createJob(job)).thenReturn(job);
        Job testJob = jobController.createJob(job);
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals(1, testJob.getStatus_id());
        assertEquals("title", testJob.getTitle());
    }
    
    @Test 
    public void testCreateJobInvalidInput(){
//        To be implemented
    }
    
    @Test
    public void testUpdateJob() throws NotFoundException {
        Job job = createJob();
        when(jobService.findJobById(1)).thenReturn(job);
        when(jobService.createJob(job)).thenReturn(job);
        Job testJob = jobController.updateJob(job);
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals(1, testJob.getStatus_id());
        assertEquals("title", testJob.getTitle());
    }
    
    @Test (expected = NotFoundException.class)
    public void testUpdateJobNotFound() throws NotFoundException {
        Job job = createJob();
        when(jobService.findJobById(1)).thenReturn(null);
        jobController.updateJob(job);
    }

    public Job createJob(){
        Job job = new Job();
        job.setAddress("address");
        job.setCity("city");
        job.setDate("01.11.2020");
        job.setPayment(120);
        job.setDescription("description");
        job.setTitle("title");
        job.setUser(new User());
        job.setStatus_id(1);
        job.setId(1);
        return job;
    }
}
