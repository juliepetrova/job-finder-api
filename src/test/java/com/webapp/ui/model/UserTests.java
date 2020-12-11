package com.webapp.ui.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserTests {

    @Test
    public void createUserTest() {
        //Arrange
        User user = new User();
        //Act
        user.setId(1);
        user.setCity("City");
        user.setCountry("Country");
        user.setDate_of_birth("2000-12-12");
        user.setEmail("email@email.com");
        user.setFirst_name("Name");
        user.setLast_name("Name");
        user.setPhone_number("12345");
        user.setUsername("username");
        user.setImage("image");
        user.setPassword("password");
        //Assert
        assertEquals(1, user.getId());
        assertEquals("City",user.getCity());
        assertEquals("Country",user.getCountry());
        assertEquals("2000-12-12",user.getDate_of_birth());
        assertEquals("email@email.com",user.getEmail());
        assertEquals("Name",user.getFirst_name());
        assertEquals("Name",user.getLast_name());
        assertEquals("12345",user.getPhone_number());
        assertEquals("username",user.getUsername());
        assertEquals("image",user.getImage());
        assertEquals("password",user.getPassword());
    }
}
