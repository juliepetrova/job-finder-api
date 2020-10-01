package com.webapp.ui.service.base;

import com.webapp.ui.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(int id);
    User findUserByEmail(String email);
    User saveUserDetails(User user);
    User updateUserDetails(User user);
    void updateRating(int user_id, double rating);

    User save(User user);
    User findByUsername(String username);
}
