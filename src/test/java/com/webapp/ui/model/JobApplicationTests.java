package com.webapp.ui.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JobApplicationTests {

    @Test
    public void createJobApplicationTest() {
        //Arrange
        JobApplication jobApplication = new JobApplication();
        Status status = new Status();
        //Act
        jobApplication.setDescription("descr");
        jobApplication.setDate("2020-12-12");
        jobApplication.setId(1);
        jobApplication.setStatus(status);
        //Assert
        assertEquals("descr", jobApplication.getDescription());
        assertEquals("2020-12-12", jobApplication.getDate());
        assertEquals(1, jobApplication.getId());
        assertEquals(status, jobApplication.getStatus());

    }
}
