package com.webapp.ui.service;

import com.webapp.ui.model.User;
import com.webapp.ui.model.UserDetailsAuth;
import com.webapp.ui.repository.UserRepository;
import com.webapp.ui.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public User saveUserDetails(User user) throws DuplicateKeyException {
        if(user == null || user.getEmail() == null){
            throw new NullPointerException();
        }
        else if(userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }else {
            throw new DuplicateKeyException("User with that email already exists");
        }
    }

    @Override
    public User updateUser(User user){
        if(user == null || user.getEmail() == null){
            throw new NullPointerException();
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public boolean checkIfUsernameExists(String username){
        return userRepository.existsUserByUsername(username);
    }

    public boolean checkIfEmailExists(String email){
        return userRepository.existsUserByEmail(email);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDetailsAuth(user);

    }
}
