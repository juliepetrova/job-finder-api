package com.webapp.ui.service;

import com.webapp.ui.model.User;
import com.webapp.ui.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void testFindById() {
        // Arrange
        User user = createUser();
        // Act
        when(userRepository.findById(1)).thenReturn(user);
        User testUser = userService.findUserById(1);
        // Assert
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
        // Arrange
        User user = null;
        // Act
        when(userRepository.findById(1)).thenReturn(user);
        User testUser = userService.findUserById(1);
        // Assert
        assertNull(testUser);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        User user = createUser();
        // Act
        when(userRepository.findByEmail("email@email.com")).thenReturn(user);
        User testUser = userService.findUserByEmail("email@email.com");
        // Assert
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
        // Arrange
        User user = null;
        // Act
        when(userRepository.findByEmail("email@email.com")).thenReturn(user);
        User testUser = userService.findUserByEmail("email@email.com");
        // Assert
        assertEquals(null, testUser);
    }

    @Test
    public void testFindAllUsers(){
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
        // Act
        when(userRepository.findAll()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testFindAllUsersWhenNone() {
        // Arrange
        List<User> users = new ArrayList<>();
        // Act
        when(userRepository.findAll()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testFindAllUsersWhenOne(){
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(createUser());
        // Act
        when(userRepository.findAll()).thenReturn(users);
        // Assert
        assertEquals(users.size(), userService.findAllUsers().size());
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    public void testSaveUser() throws Exception {
        // Arrange
        User u = createUser();
        // Act
        when(userRepository.save(u)).thenReturn(u);
        User testUser = userService.saveUserDetails(u);
        // Assert
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
        // Arrange
        User user = createUser();
        // Act
        when(userRepository.findByEmail("email@email.com")).thenReturn(user);
        userService.saveUserDetails(user);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        User u = createUser();
        // Act
        when(userRepository.save(u)).thenReturn(u);
        User testUser = userService.updateUser(u);
        // Assert
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test
    public void testFindByUsername(){
        // Arrange
        User user = createUser();
        // Act
        when(userRepository.findByUsername("username")).thenReturn(user);
        User testUser = userService.findByUsername("username");
        // Assert
        assertEquals(1, testUser.getId());
        assertEquals("first", testUser.getFirst_name());
        assertEquals("last", testUser.getLast_name());
        assertEquals("1234", testUser.getPhone_number());
        assertEquals("12.12.2000", testUser.getDate_of_birth());
        assertEquals("Austria", testUser.getCountry());
        assertEquals("Vienna", testUser.getCity());
    }

    @Test
    public void testFindByUsernameDoesNotExist() {
        // Arrange
        User user = null;
        // Act
        when(userRepository.findByUsername("username")).thenReturn(user);
        User testUser = userService.findByUsername("username");
        // Assert
        assertEquals(null, testUser);
    }

    @Test
    public void testCheckIfUsernameExists() {
        // Act
        when(userRepository.existsUserByUsername("username")).thenReturn(true);
        boolean testUser = userService.checkIfUsernameExists("username");
        // Assert
        assertEquals(true, testUser);
    }

    @Test
    public void testCheckIfUsernameDoesNotExist() {
        // Act
        when(userRepository.existsUserByUsername("username")).thenReturn(false);
        boolean testUser = userService.checkIfUsernameExists("username");
        // Assert
        assertEquals(false, testUser);
    }
    @Test
    public void testCheckIfEmailExists() {
        // Act
        when(userRepository.existsUserByEmail("email@email.com")).thenReturn(true);
        boolean testUser = userService.checkIfEmailExists("email@email.com");
        // Assert
        assertEquals(true, testUser);
    }

    @Test
    public void testCheckIfEmailDoesNotExist() {
        // Act
        when(userRepository.existsUserByEmail("email@email.com")).thenReturn(false);
        boolean testUser = userService.checkIfEmailExists("email@email.com");
        // Assert
        assertEquals(false, testUser);
    }

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        User user = createUser();
        // Act
        when(userRepository.findByUsername("username")).thenReturn(user);
        UserDetails testUser = userService.loadUserByUsername("username");
        // Assert
        assertEquals("username", testUser.getUsername());
        assertEquals("password", testUser.getPassword());
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
        user.setPassword("password");
        return user;
    }

}
