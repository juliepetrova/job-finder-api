package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.ApplicantService;
import com.webapp.ui.service.base.UserService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;
    @Mock
    ApplicantService applicantService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        // Act
        when(userService.findAllUsers()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUsersWhenEmpty() {
        // Arrange
        List<User> users = new ArrayList<>();
        // Act
        when(userService.findAllUsers()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUsersWhenSingleUser() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(createUser());
        // Act
        when(userService.findAllUsers()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUserById() throws NotFoundException {
        // Arrange
        User user = createUser();
        // Act
        when(userService.findUserById(1)).thenReturn(user);
        User testUser = userController.getUser(1);
        // Assert
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test(expected = NotFoundException.class)
    public void testGetUserByIdInvalid() throws NotFoundException {
        when(userService.findUserById(1)).thenReturn(null);
        userController.getUser(1);
    }

    @Test
    public void testGetJobsByUser() {
        // Arrange
        User user = createUser();
        Job job = new Job();
        job.setAddress("address");
        job.setCity("city");
        job.setDate("01.11.2020");
        job.setPayment(120);
        job.setDescription("description");
        job.setTitle("title");
        job.setUser(user);
        job.setStatus(new Status());
        List<Job> jobs = new ArrayList<>();
        jobs.add(job);
        user.setJobs(jobs);
        // Act
        when(userService.findUserById(1)).thenReturn(user);
        // Assert
        assertEquals(1, userController.getJobsByUser(1).size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetJobsByUserWhenEmpty() {
        // Arrange
        User user = createUser();
        // Act
        when(userService.findUserById(1)).thenReturn(user);
        // Assert
        userController.getJobsByUser(1);
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        User user = createUser();
        // Act
        when(userService.updateUser(user)).thenReturn(user);
        User testUser = userController.updateUser(user);
        // Assert
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateUserNotExisting() throws Exception {
        // Arrange
        User user = createUser();
        // Act (expect exception)
//        when(userService.saveUserDetails(user)).thenReturn(null);
        user.setId(211);
        userController.updateUser(user);
    }

    public User createUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPhone_number("1234");
        user.setFirst_name("first");
        user.setLast_name("last");
        user.setEmail("email@email.com");
        user.setDate_of_birth("12.12.2000");
        user.setCountry("Austria");
        user.setCity("Vienna");
        return user;
    }

    //    Applicant
    @Test
    public void testGetApplicantById() throws NotFoundException {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setSkills("skills");
        applicant.setRating(4);
        applicant.setExperience("experience");
        // Act
        when(applicantService.findApplicantById(1)).thenReturn(applicant);
        Applicant testApplicant = userController.getApplicant(1);
        // Assert
        assertEquals(1, testApplicant.getId());
        assertEquals("skills", testApplicant.getSkills());
        assertEquals(4, testApplicant.getRating(), 1);

        assertEquals("experience", testApplicant.getExperience());

    }

    @Test(expected = NotFoundException.class)
    public void testGetApplicantByIdInvalid() throws NotFoundException {
        when(applicantService.findApplicantById(1)).thenReturn(null);
        userController.getApplicant(1);
    }

    @Test
    public void testCreateApplicant() throws Exception {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setSkills("skills");
        applicant.setRating(4);
        applicant.setExperience("experience");
        // Act
        when(userService.findUserById(1)).thenReturn(createUser());
        when(applicantService.createApplicant(applicant)).thenReturn(applicant);
        Applicant testApplicant = userController.createApplicant(applicant);
        // Assert
        assertEquals(1, testApplicant.getId());
        assertEquals("skills", testApplicant.getSkills());
        assertEquals(4, testApplicant.getRating(), 1);
        assertEquals("experience", testApplicant.getExperience());

    }

    @Test(expected = Exception.class)
    public void testCreateApplicantIncorrectInput() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setSkills("skills");
        applicant.setRating(4);
        applicant.setExperience("experience");
        when(userService.findUserById(1)).thenReturn(null);
        userController.createApplicant(applicant);
    }


}
