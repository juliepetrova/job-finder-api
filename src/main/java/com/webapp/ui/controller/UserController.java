package com.webapp.ui.controller;

import com.webapp.ui.model.User;
import com.webapp.ui.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<Integer, User> users;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
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
//    @Autowired
//    private UserRepository userRepository;

//    @GetMapping
//    public String getUsers(){
//        return "get users was called";
//    }

    @GetMapping
    public List<User> getUsersPagination(@RequestParam(value="page", defaultValue = "1") int page,
                                   @RequestParam(value="limit", defaultValue = "50") int limit,
                                   @RequestParam(value="sort", required = false) String sort){

        return userService.findAllUsers();
//        return "get users was called from page " + page + " and limit " + limit;
    }

    @GetMapping(path="/{userId}", produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User getUser(@PathVariable String userId){

        User returnValue = new User();
        returnValue.setFirst_name("Mark");
        returnValue.setEmail("test@email.com");
        return returnValue;
    }

    @PostMapping
    public String createUser(){
        return "create user was called";
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }
}
