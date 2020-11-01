package com.webapp.ui.service.base;

import com.webapp.ui.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(int id);
    User findByUsername(String username);
    User findUserByEmail(String email);
    User saveUserDetails(User user) throws Exception;
    void updateRating(int user_id, double rating);
    void deleteUser(int user_id);
}
