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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        user.setId(211);
        userController.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        // Arrange
        int userId = 1;
        // Act
        userController.deleteUser(userId);
    }

//    @Test
//    public void testRegister() {
//        // Arrange
//        User user = createUser();
//        // Act
//        when(userService.checkIfUsernameExists(user.getUsername())).thenReturn(false);
//        when(userService.checkIfEmailExists(user.getEmail())).thenReturn(false);
//        when(userService.saveUserDetails(user)).thenReturn(user);
//        ResponseEntity testUser = userController.registerUser(user);
//        // Assert
//        assertEquals(ResponseEntity.ok(user), testUser);
//    }

    @Test
    public void testRegisterDuplicatedEmail() {
        // Arrange
        User user = createUser();
        ResponseEntity re = new ResponseEntity(null, HttpStatus.UNPROCESSABLE_ENTITY);
        // Act
        when(userService.checkIfUsernameExists(user.getUsername())).thenReturn(true);
        ResponseEntity testUser = userController.registerUser(user);
        // Assert
        assertEquals(re, testUser);
    }

    @Test
    public void testGetUserByUsername() {
        // Arrange
        User user = createUser();
        // Act
        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        ResponseEntity testUser = userController.getUserByUsername(user.getUsername());
        // Assert
        assertEquals(ResponseEntity.ok(user), testUser);
    }

    @Test
    public void testGetUserByUsernameNotExisting() {
        // Arrange
        User user = createUser();
        ResponseEntity re = new ResponseEntity(null, HttpStatus.UNPROCESSABLE_ENTITY);
        // Act
        when(userService.findByUsername(user.getUsername())).thenReturn(null);
        ResponseEntity testUser = userController.getUserByUsername(user.getUsername());
        // Assert
        assertEquals(re, testUser);
    }

    @Test
    public void testGetPastJobs() {
        // Arrange
        User user = createUser();
        List<Job> jobs = new ArrayList<>();
        user.setJobs(jobs);
        // Act
        when(userService.findUserById(1)).thenReturn(user);
        List<Job> testJobs = userController.getPastJobsByUser(1);
        // Assert
        assertEquals(0, testJobs.size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetPastJobsNull() {
        // Arrange
        User user = createUser();
        // Act
        when(userService.findUserById(1)).thenReturn(user);
        List<Job> testJobs = userController.getPastJobsByUser(1);
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

    @Test(expected = NotFoundException.class)
    public void testCreateApplicantIncorrectInput() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setSkills("skills");
        applicant.setRating(4);
        applicant.setExperience("experience");
        when(userService.findUserById(1)).thenReturn(null);
        userController.createApplicant(applicant);
    }

    @Test(expected = InputMismatchException.class)
    public void testCreateApplicantNegativeId() throws Exception {
        Applicant applicant = new Applicant();
        userController.createApplicant(applicant);
    }

    @Test
    public void testUpdateApplicant() throws NotFoundException {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setSkills("skills");
        applicant.setRating(4);
        applicant.setExperience("experience");
        // Act
        when(applicantService.findApplicantById(1)).thenReturn(applicant);
        when(applicantService.updateApplicant(applicant)).thenReturn(applicant);
        Applicant testApplicant = userController.updateApplicant(applicant);
        // Assert
        assertEquals(1, testApplicant.getId());
        assertEquals("skills", testApplicant.getSkills());
        assertEquals(4, testApplicant.getRating(), 1);
        assertEquals("experience", testApplicant.getExperience());
    }

    @Test (expected = NotFoundException.class)
    public void testUpdateApplicantNotFound() throws NotFoundException {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        // Act
        when(applicantService.findApplicantById(1)).thenReturn(null);
        Applicant testApplicant = userController.updateApplicant(applicant);
    }

    @Test
    public void testUpdateRating() throws NotFoundException {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        // Act
        when(applicantService.findApplicantById(1)).thenReturn(applicant);
        when(applicantService.updateApplicant(applicant)).thenReturn(applicant);
        userController.updateRating(1, 10);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateRatingUserNotFound() throws NotFoundException {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setId(1);
        // Act
        when(applicantService.findApplicantById(1)).thenReturn(null);
        userController.updateRating(1, 10);
    }
}
