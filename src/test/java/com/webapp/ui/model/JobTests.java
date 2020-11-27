package com.webapp.ui.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JobTests {

    @Test
    public void createJob() {
        // Arrange
        Job job = new Job();
        // Act
        job.setTitle("Test job");
        job.setDescription("This is example");
        job.setPayment(32.00);
        job.setDate("12-12-2020");
        job.setCity("Eindhoven");
        job.setAddress("Parkstraat 12");
        // Assert
        assertEquals("Test job", job.getTitle());
        assertEquals("This is example", job.getDescription());
        assertEquals("12-12-2020", job.getDate());
        assertEquals("Eindhoven", job.getCity());
        assertEquals("Parkstraat 12", job.getAddress());
    }
}
