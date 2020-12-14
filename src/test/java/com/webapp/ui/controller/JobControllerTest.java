package com.webapp.ui.controller;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.JobService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
    public void testGetJobById() throws NotFoundException {
        // Arrange
        Job job = createJob();
        // Act
        when(jobService.findJobById(1)).thenReturn(job);
        Job testJob = jobController.getJob(1);
        // Assert
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }
    
    @Test (expected = NotFoundException.class)
    public void testGetJobByInvalidId() throws NotFoundException {
        // Expect failure
        when(jobService.findJobById(1)).thenReturn(null);
        jobController.getJob(1);
    }
    
    @Test
    public void testGetUserByJob(){
        // Arrange
        Job job = createJob();
        User u = new User();
        job.setUser(u);
        // Act
        when(jobService.findJobById(1)).thenReturn(job);
        User user = jobController.getUserByJob(1);
        // Assert
        assertEquals(u, user);

    }
    
    @Test
    public void testCreateJob(){
        // Arrange
        Job job = createJob();
        // Act
        when(jobService.createJob(job)).thenReturn(job);
        Job testJob = jobController.createJob(job);
        // Assert
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }
    

    @Test
    public void testUpdateJob() throws NotFoundException {
        // Arrange
        Job job = createJob();
        // Act
        when(jobService.findJobById(1)).thenReturn(job);
        when(jobService.createJob(job)).thenReturn(job);
        Job testJob = jobController.updateJob(job);
        // Assert
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }
    
    @Test (expected = NotFoundException.class)
    public void testUpdateJobNotFound() throws NotFoundException {
        Job job = createJob();
        when(jobService.findJobById(1)).thenReturn(null);
        jobController.updateJob(job);
    }

    @Test
    public void testGetAllJobsStatistics() {
        // Arrange
        Map<String, String> stats = new Hashtable<>();
        stats.put("allJobs", "12");
        stats.put("mostPopularCity", "Eindhoven");
        stats.put("totalEarnings", "120");
        // Act
        when(jobService.countAllJobs()).thenReturn(Long.valueOf("12"));
        when(jobService.getMostPopularCity()).thenReturn("Eindhoven");
        when(jobService.getTotalEarnings()).thenReturn("120");
        Map<String, String> results = jobController.getAllJobsStatistics();
        // Assert
        assertEquals(stats.size(), results.size());
        assertEquals(stats.containsValue("120"), results.containsValue("120"));
        assertEquals(stats.containsValue("Eindhoven"), results.containsValue("Eindhoven"));
        assertEquals(stats.containsValue("12"), results.containsValue("12"));
    }

    @Test
    public void testChangeStatus() {
        // Arrange
        Job job = createJob();
        // Act
        when(jobService.findJobById(1)).thenReturn(job);
        when(jobService.createJob(job)).thenReturn(job);
        jobController.changeStatus(1, 2);
    }

    @Test
    public void testDeleteJob(){
        // Arrange
        int jobId = 1;
        // Act
        jobController.deleteJob(jobId);
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
        job.setStatus(new Status());
        job.setId(1);
        return job;
    }
}
