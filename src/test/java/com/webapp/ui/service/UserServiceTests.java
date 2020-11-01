package com.webapp.ui.service;

import com.webapp.ui.model.User;
import com.webapp.ui.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void testFindById() {
        when(userRepository.findById(1)).thenReturn(createUser());
        User testUser = userService.findUserById(1);
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("email@email.com", testUser.getEmail());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test
    public void testFindByIdDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(null);
        User testUser = userService.findUserById(1);
        assertEquals(null, testUser);
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail("email@email.com")).thenReturn(createUser());
        User testUser = userService.findUserByEmail("email@email.com");
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test
    public void testFindByEmailDoesNotExist() {
        when(userRepository.findByEmail("email@email.com")).thenReturn(null);
        User testUser = userService.findUserByEmail("email@email.com");
        assertEquals(null, testUser);
    }

    @Test
    public void testFindAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testFindAllUsersWhenNone() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testFindAllUsersWhenOne(){
        List<User> users = new ArrayList<>();
        users.add(createUser());
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testSaveUser() throws Exception {
        User u = createUser();
        when(userRepository.save(u)).thenReturn(u);
        User testUser = userService.saveUserDetails(u);
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test(expected = NullPointerException.class)
    public void testSaveUserNull() throws Exception {
        User testUser = new User();
        userService.saveUserDetails(testUser);
    }

    @Test(expected = Exception.class)
    public void testSaveUserWithDuplicatedEmail() throws Exception {
        when(userRepository.findByEmail("email@email.com")).thenReturn(createUser());
        userService.saveUserDetails(createUser());
    }

    public User createUser(){
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

}
