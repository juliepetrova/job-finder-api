package com.webapp.ui.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@RunWith(MockitoJUnitRunner.class)
public class JobTests {

    @Test
    void createJob() {
        Job job = new Job();
        job.setTitle("Test job");
        job.setDescription("This is example");
        job.setPayment(32.00);
        job.setDate("12-12-2020");
        job.setCity("Eindhoven");
        job.setAddress("Parkstraat 12");
        Assert.hasText("Test job", job.getTitle());
        Assert.hasText("This is example", job.getDescription());
        Assert.hasText("12-12-2020", job.getDate());
        Assert.hasText("Eindhoven", job.getCity());
        Assert.hasText("Parkstraat 12", job.getAddress());

    }
}
