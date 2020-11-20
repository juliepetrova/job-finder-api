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
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers(){
        List<User> users = new ArrayList<>();
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        when(userService.findAllUsers()).thenReturn(users);
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUsersWhenEmpty() {
        List<User> users = new ArrayList<>();
        when(userService.findAllUsers()).thenReturn(users);
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUsersWhenSingleUser(){
        List<User> users = new ArrayList<>();
        users.add(createUser());
        when(userService.findAllUsers()).thenReturn(users);
        assertEquals(users.size(), userController.getUsers().size());
    }

    @Test
    public void testGetUserById() throws NotFoundException {
        User user = createUser();
        when(userService.findUserById(1)).thenReturn(user);
        User testUser = userController.getUser(1);
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
    public void testGetJobsByUser(){
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
        when(userService.findUserById(1)).thenReturn(user);
        assertEquals(1, userController.getJobsByUser(1).size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetJobsByUserWhenEmpty(){
        User user = createUser();
        when(userService.findUserById(1)).thenReturn(user);
        userController.getJobsByUser(1);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = createUser();
        when(userService.saveUserDetails(user)).thenReturn(user);
        User testUser = userController.updateUser(user);
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test (expected = NotFoundException.class)
    public void testUpdateUserNotExisting() throws Exception {
        User user = createUser();
        when(userService.saveUserDetails(user)).thenReturn(null);
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
    Applicant applicant = new Applicant();
    applicant.setId(1); applicant.setSkills("skills"); applicant.setRating(4); applicant.setPicture("picture"); applicant.setExperience("experience");
    when(applicantService.findApplicantById(1)).thenReturn(applicant);
    Applicant testApplicant = userController.getApplicant(1);
    assertEquals(1, testApplicant.getId());
    assertEquals("skills", testApplicant.getSkills());
    assertEquals(4, testApplicant.getRating(),1);
    assertEquals("picture", testApplicant.getPicture());
    assertEquals("experience", testApplicant.getExperience());

}

    @Test(expected = NotFoundException.class)
    public void testGetApplicantByIdInvalid() throws NotFoundException {
        when(applicantService.findApplicantById(1)).thenReturn(null);
        userController.getApplicant(1);
    }

    @Test
    public void testCreateApplicant() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1); applicant.setSkills("skills"); applicant.setRating(4); applicant.setPicture("picture"); applicant.setExperience("experience");
        when(userService.findUserById(1)).thenReturn(createUser());
        when(applicantService.createApplicant(applicant)).thenReturn(applicant);
        Applicant testApplicant = userController.createApplicant(applicant);
        assertEquals(1, testApplicant.getId());
        assertEquals("skills", testApplicant.getSkills());
        assertEquals(4, testApplicant.getRating(),1);
        assertEquals("picture", testApplicant.getPicture());
        assertEquals("experience", testApplicant.getExperience());

    }

    @Test(expected = Exception.class)
    public void testCreateApplicantIncorrectInput() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1); applicant.setSkills("skills"); applicant.setRating(4); applicant.setPicture("picture"); applicant.setExperience("experience");
        when(userService.findUserById(1)).thenReturn(null);
        userController.createApplicant(applicant);
    }


}
