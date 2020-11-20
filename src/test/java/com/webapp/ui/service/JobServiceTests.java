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
        when(jobRepository.findById(1)).thenReturn(createJob());
        Job testJob = jobService.findJobById(1);
        assertEquals("address", testJob.getAddress());
        assertEquals("city", testJob.getCity());
        assertEquals("01.11.2020", testJob.getDate());
        assertEquals("description", testJob.getDescription());
        assertEquals(120, testJob.getPayment(), 1);
        assertEquals("title", testJob.getTitle());
    }

    @Test
    public void getJobByWrongId(){
        when(jobRepository.findById(1)).thenReturn(null);
        Job testJob = jobService.findJobById(1);
        assertNull(testJob);
    }

    @Test
    public void testGetJobsByUserId(){
        when(jobRepository.findByUser(1)).thenReturn(new ArrayList<>());
        List<Job> testJobs = jobService.findJobsByUserId(1);
        assertEquals(new ArrayList<>(), testJobs);
        assertEquals((new ArrayList<>()).size(), testJobs.size());
    }

    @Test
    public void testGetJobsByWrongUserId(){
        when(jobRepository.findByUser(1)).thenReturn(null);
        List<Job> testJobs = jobService.findJobsByUserId(1);
        assertNull(testJobs);
    }

    @Test
    public void testGetJobsByCity(){
        List<Job> jobs = new ArrayList<>();
        jobs.add(createJob());
        jobs.add(createJob());
        jobs.add(createJob());
        when(jobRepository.findByCity("city")).thenReturn(jobs);
        List<Job> testJobs = jobService.findJobsByCity("city");
        assertEquals(jobs, testJobs);
        assertEquals(jobs.size(), testJobs.size());
    }

    @Test
    public void testGetJobsByCityWhenZero(){
        List<Job> jobs = new ArrayList<>();
        when(jobRepository.findByCity("city")).thenReturn(jobs);
        List<Job> testJobs = jobService.findJobsByCity("city");
        assertEquals(jobs, testJobs);
        assertEquals(jobs.size(), testJobs.size());
    }

    @Test
    public void testSaveJob(){
        Job job = createJob();
        when(jobRepository.save(job)).thenReturn(job);
        Job testJob = jobService.createJob(job);
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
