package com.webapp.ui.controller;

import com.webapp.ui.model.Applicant;
import com.webapp.ui.model.Job;
import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.User;
import com.webapp.ui.service.base.ApplicantService;
import com.webapp.ui.service.base.UserService;
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
    public User registerUser(@RequestBody User user){
        return userService.saveUserDetails(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
        public String login(@RequestBody Map<String, String> json) throws ServletException {
            if(json.get("username") == null || json.get("password") == null){
                throw new ServletException("Please fill in username and password!");
            }
            String username = json.get("username");
            String password = json.get("password");

            User user = userService.findByUsername(username);

            if(user == null){
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
    public List<User> getUsersPagination(@RequestParam(value="page", defaultValue = "1") int page,
                                   @RequestParam(value="limit", defaultValue = "50") int limit,
                                   @RequestParam(value="sort", required = false) String sort){

        return userService.findAllUsers();
    }

    @GetMapping(path="/{userId}", produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User getUser(@PathVariable int userId){

        return userService.findUserById(userId);
    }

    @GetMapping (path = "/{user_id}/jobs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<Job> getJobsByUser(@PathVariable int user_id){
       User user = userService.findUserById(user_id);
       return user.getJobs();
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.saveUserDetails(user);
    }

    @DeleteMapping(path = "/{user_id}")
    public void deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
    }


//    Applicant
@PostMapping (path = "/applicant")
public Applicant createApplicant(@RequestBody Applicant applicant) {

        return applicantService.createApplicant(applicant);

    }

    @GetMapping (path = "/applicant/{user_id}")
        public Applicant getApplicant (@PathVariable int user_id){
            return applicantService.findApplicantById(user_id);
        }

//    @GetMapping (path = "/{user_id}/applicationsJobs", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public Map<JobApplication,Job> getApplications (@PathVariable int user_id){
//        Applicant applicant = applicantService.findApplicantById(user_id);
//        Set<JobApplication> applications = applicant.getJobApplications();
//        Map<JobApplication, Job> map = new HashMap<JobApplication, Job>();
//        for (JobApplication appl:
//                applications) {
//            map.put(appl, appl.getJob());
//        }
//        return map;
//    }
}
