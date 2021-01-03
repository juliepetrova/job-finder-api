package com.webapp.ui.controller;

import java.util.*;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobReport;
import com.webapp.ui.repository.JobReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobReportControllerTest {

    @Mock
    JobReportRepository jobReportRepository;

    @InjectMocks
    JobReportController jobReportController;

    @Test
    public void testCreateJobReport(){
        // Arrange
        JobReport jobReport = createJobReport();
        // Act
        when(jobReportRepository.save(jobReport)).thenReturn(jobReport);
        ResponseEntity testResponse = jobReportController.saveJobReport(jobReport);
        JobReport testJobReport = (JobReport) testResponse.getBody();
        // Assert
        assertEquals("2020-12-12", testJobReport.getDate());
        assertEquals("This is description", testJobReport.getDescription());
        assertEquals(1, jobReport.getId());
    }

    @Test
    public void testGetJobReportById(){
        // Arrange
        JobReport jobReport = createJobReport();
        // Act
        when(jobReportRepository.findById(1)).thenReturn(jobReport);
        ResponseEntity testResponse = jobReportController.getById(1);
        JobReport testJobReport = (JobReport) testResponse.getBody();
        // Assert
        assertEquals("2020-12-12", testJobReport.getDate());
        assertEquals("This is description", testJobReport.getDescription());
        assertEquals(1, jobReport.getId());
    }

    @Test
    public void testGetJobReportByWrongId(){
        // Arrange
        JobReport jobReport = createJobReport();
        ResponseEntity result = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        // Act
        when(jobReportRepository.findById(1)).thenReturn(null);
        ResponseEntity testResponse = jobReportController.getById(1);
        // Assert
        assertEquals(result, testResponse);
    }

    @Test
    public void testGetAllJobReports(){
        // Arrange
        List<JobReport> jobReportList = new ArrayList<>();
        JobReport jobReport = createJobReport();
        jobReportList.add(jobReport);
        jobReportList.add(jobReport);
        jobReportList.add(jobReport);
        // Act
        when(jobReportRepository.findAll()).thenReturn(jobReportList);
        List<JobReport> testList = jobReportController.getAll();
        // Assert
        assertEquals(3, testList.size());
    }


    public JobReport createJobReport(){
        JobReport jobReport = new JobReport();
        Job job = new Job();
        jobReport.setId(1);
        jobReport.setDate("2020-12-12");
        jobReport.setDescription("This is description");
        jobReport.setJob(job);
        return jobReport;
    }

}
