package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.ApplicantService;
import com.webapp.ui.service.base.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<Integer, User> users;

    @Autowired
    UserService userService;
    @Autowired
    ApplicantService applicantService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userService.saveUserDetails(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> json) throws ServletException {
        if (json.get("username") == null || json.get("password") == null) {
            throw new ServletException("Please fill in username and password!");
        }
        String username = json.get("username");
        String password = json.get("password");

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new ServletException("Username not found!");
        }
        return "Not implemented";

//            String pwd = user.getPassword();
//            if(!password.equals(pwd)){
//                throw new ServletException("Invalid password");
//            }
//
//            return Jwts.builder().setSubject(username).clain("roles", "user").setIssuedAt(new Date())
//                    .signWith(SignatureAlgorithm.HS256, "secret").compact();
    }


    @GetMapping
    public List<User> getUsers() {
//            @RequestParam(value="page", defaultValue = "1") int page,
//            @RequestParam(value="limit", defaultValue = "50") int limit,
        return userService.findAllUsers();
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User getUser(@PathVariable int userId) throws NotFoundException {
        User user = userService.findUserById(userId);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundException("User with the provided id not found");
        }
    }

    @GetMapping(path = "/{user_id}/jobs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Job> getJobsByUser(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        if(user.getJobs() != null) {
            return user.getJobs();
        }else {
            throw new NullPointerException("This user has not posted any jobs.");
        }
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws NotFoundException {
        User found = userService.saveUserDetails(user);
        if(found != null) {
            return found;
        }else{
            throw new NotFoundException("User with this id was not found!");
        }
    }

    @DeleteMapping(path = "/{user_id}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }


    //    Applicant
    @PostMapping(path = "/applicant")
    public Applicant createApplicant(@RequestBody Applicant applicant) throws NotFoundException {
        if(applicant.getId() < 1){
            throw new InputMismatchException("User with the specified id does not exist");
        }else if (userService.findUserById(applicant.getId()) != null) {
            return applicantService.createApplicant(applicant);
        }else {
            throw new NotFoundException("There is no user corresponding to this applicant id.");
        }

    }

    @GetMapping(path = "/applicant/{user_id}")
    public Applicant getApplicant(@PathVariable int userId) throws NotFoundException {
        if(applicantService.findApplicantById(userId) != null) {
            return applicantService.findApplicantById(userId);
        } else {
            throw new NotFoundException("Applicant not found");
        }
    }


}
