package com.webapp.ui.service;

import com.webapp.ui.model.User;
import com.webapp.ui.repository.UserRepository;
import com.webapp.ui.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User saveUserDetails(User user) throws Exception {
        if(user == null || user.getEmail() == null){
            throw new NullPointerException();
        }
        else if(userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }else {
            throw new Exception("User with that email already exists");
        }
    }

    @Override
    public void updateRating(int user_id, double rating) { }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(int user_id) {
        userRepository.deleteById(user_id);
    }


}
