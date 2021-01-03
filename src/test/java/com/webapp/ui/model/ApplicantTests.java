package com.webapp.ui.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ApplicantTests {

    @Test
    public void createApplicantTest() {
        //Arrange
        Applicant applicant = new Applicant();
        //Act
        applicant.setNumRatings(0);
        applicant.setRating(1);
        applicant.setExperience("Exp");
        applicant.setSkills("skills");
        applicant.setId(1);
        //Assert
        assertEquals("Exp", applicant.getExperience());
        assertEquals("skills", applicant.getSkills());
        assertEquals(0, applicant.getNumRatings());
        assertEquals(1, applicant.getRating(), 1);
        assertEquals(1, applicant.getId());

    }
}
