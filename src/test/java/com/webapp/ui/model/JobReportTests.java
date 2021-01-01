package com.webapp.ui.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JobReportTests {

    @Test
    public void createJobReportTest() {
        // Arrange
        JobReport jobReport = new JobReport();
        Job job = new Job();
        // Act
        jobReport.setId(1);
        jobReport.setDate("2020-12-12");
        jobReport.setDescription("This is description");
        jobReport.setJob(job);
        // Assert
        assertEquals("This is description", jobReport.getDescription());
        assertEquals("2020-12-12", jobReport.getDate());
        assertEquals(1, jobReport.getId());
    }

}
