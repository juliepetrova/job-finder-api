package com.webapp.ui.service;

import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTests {

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobServiceImpl jobService;

    @Test
    public void testGetJobById(){
        // Arrange
        Job job = createJob();
        // Act
        when(jobRepository.findById(1)).thenReturn(job);
        Job testJob = jobService.findJobById(1);
        // Assert
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }

    @Test
    public void getJobByWrongId(){
        // Arrange
        Job job = null;
        // Act
        when(jobRepository.findById(1)).thenReturn(job);
        Job testJob = jobService.findJobById(1);
        // Assert
        assertNull(testJob);
    }

    @Test
    public void testGetJobsByUserId(){
        // Arrange
        List<Job> jobs = new ArrayList<>();
        // Act
        when(jobRepository.findByUser(1)).thenReturn(jobs);
        List<Job> testJobs = jobService.findJobsByUserId(1);
        // Assert
        assertEquals(jobs, testJobs);
        assertEquals(jobs.size(), testJobs.size());
    }

    @Test
    public void testGetJobsByWrongUserId(){
        // Arrange
        List<Job> jobs = null;
        // Act
        when(jobRepository.findByUser(1)).thenReturn(jobs);
        List<Job> testJobs = jobService.findJobsByUserId(1);
        // Assert
        assertNull(testJobs);
    }

    @Test
    public void testGetJobsByCity(){
        // Arrange
        List<Job> jobs = new ArrayList<>();
        jobs.add(createJob());
        jobs.add(createJob());
        jobs.add(createJob());
        Page<Job> page = new PageImpl<>(jobs);
        // Act
        Pageable firstPage = (Pageable) PageRequest.of(0, 10);
        when(jobRepository.findByCity("city", firstPage)).thenReturn(page);
        Page<Job> testJobs = jobService.findJobsByCity("city", firstPage);
        // Assert
        assertEquals(page, testJobs);
    }

    @Test
    public void testGetJobsByCityWhenZero(){
        // Arrange
        List<Job> jobs = new ArrayList<>();
        Page<Job> page = new PageImpl<>(jobs);
        // Act
        Pageable firstPage = (Pageable) PageRequest.of(0, 10);
        when(jobRepository.findByCity("city", firstPage)).thenReturn((page));
        Page<Job> testJobs =jobService.findJobsByCity("city", firstPage);
        // Assert
        assertEquals(page, testJobs);

    }

    @Test
    public void testSaveJob(){
        // Arrange
        Job job = createJob();
        // Act
        when(jobRepository.save(job)).thenReturn(job);
        Job testJob = jobService.createJob(job);
        // Assert
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }

    @Test(expected = NullPointerException.class)
    public void testSaveJobNull(){
        Job job = new Job();
        jobService.createJob(job);
    }

    public Job createJob() {
        Job job = new Job();
        job.setAddress("address");
        job.setCity("city");
        job.setDate("01.11.2020");
        job.setPayment(120);
        job.setDescription("description");
        job.setTitle("title");
        job.setUser(new User());
        job.setStatus(new Status());
        return job;
    }
}
