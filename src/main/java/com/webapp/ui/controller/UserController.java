package com.webapp.ui.controller;

import com.webapp.ui.model.*;
import com.webapp.ui.service.UserServiceImpl;
import com.webapp.ui.service.base.ApplicantService;
import com.webapp.ui.service.base.UserService;
import com.webapp.ui.util.JwtUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.checkIfEmailExists(user.getEmail()) || userService.checkIfUsernameExists(user.getUsername())){
            return new ResponseEntity(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            return ResponseEntity.ok(userService.saveUserDetails(user));
        }
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());


        final String jwt = jwtTokenUtil.generateToken(userDetails);

//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        UserDetailsAuth userDetailsAuth = (UserDetailsAuth) userDetails;

        return ResponseEntity.ok(new AuthenticationResponse(jwt,
                userDetailsAuth.getUser()));
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

    @GetMapping(path = "/username/{username}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws NotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            throw new NotFoundException("User with the provided id not found");
        }
    }


    @GetMapping(path = "/{userId}/jobs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Job> getJobsByUser(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        if(user.getJobs() != null) {
            List<Job> jobs = new ArrayList<>();
            for (Job job :
                    user.getJobs()) {
                if (job.getStatus().getId() != 2) {
                    jobs.add(job);
                }
            }
            return jobs;
        }else {
            throw new NullPointerException("This user has not posted any jobs.");
        }
    }

    @GetMapping(path = "/{userId}/pastjobs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Job> getPastJobsByUser(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        if (user.getJobs() != null) {
            List<Job> pastJobs = new ArrayList<>();
            for (Job job :
                    user.getJobs()) {
                if (job.getStatus().getId() == 2) {
                    pastJobs.add(job);
                }
            }
            return pastJobs;
        } else {
            throw new NullPointerException("This user has 0 past jobs yet.");
        }
    }



    @PutMapping
    public User updateUser(@RequestBody User user) throws NotFoundException {
        User found = userService.updateUser(user);
        if(found != null) {
            return found;
        }else{
            throw new NotFoundException("User with this id was not found!");
        }
    }

    @DeleteMapping(path = "/{userId}")
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

    @GetMapping(path = "/applicant/{id}")
    public Applicant getApplicant(@PathVariable int id) throws NotFoundException {
        if(applicantService.findApplicantById(id) != null) {
            return applicantService.findApplicantById(id);
        } else {
            throw new NotFoundException("Applicant not found");
        }
    }

    @PutMapping (path = "/applicant")
    public Applicant updateApplicant(@RequestBody Applicant applicant) throws NotFoundException {
        Applicant found = applicantService.updateApplicant(applicant);
        if(found != null) {
            return found;
        }else{
            throw new NotFoundException("Applicant with this id was not found!");
        }
    }


}
