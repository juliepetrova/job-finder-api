package com.webapp.ui.service.base;

import com.webapp.ui.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(int id);
    User findByUsername(String username);
    User findUserByEmail(String email);
    User saveUserDetails(User user);
    void updateRating(int userId, double rating);
    void deleteUser(int userId);
    UserDetails loadUserByUsername(String username);
    boolean checkIfUsernameExists(String username);
    boolean checkIfEmailExists (String email);
}
