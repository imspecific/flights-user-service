package com.flights.user.controller;

import com.flights.user.exception.UserNotFoundException;
import com.flights.user.model.User;
import com.flights.user.repository.UserCrudRepository;
import com.flights.user.service.UserCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("poc-user-service")
public class UserCrudController {


    @Autowired
    private UserCrudService userService;

    @Autowired
    private UserCrudRepository userRepo;

    // /poc-user-service/login
    @GetMapping(value = "/login")
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login")
    public void userLogin(@RequestParam("userName") String userName,
                          @RequestParam("password") String password, @RequestParam("userType") String userType, Model model) {

        try {
            Optional<User> user = userRepo.findByUserNameAndPasswordAndUserType(userName, password, userType);
            if (user.isPresent()) {
                log.info("Logged in successfully.");
            } else {
                log.info("Login Failed. Please try again !!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // poc-user-service/add-user
    @PostMapping(value = "/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Optional<User> userById = userRepo.findById(user.getUserId());
        if (userById.isPresent())
            throw new UserNotFoundException("User id previously exists.");
        else {
            userService.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    // poc-user-service/all-users
    @GetMapping(value = "/all-users")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    // poc-user-service/update-user
    @PutMapping(value = "/update-user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userById = userRepo.findById(user.getUserId());
        if (userById.isPresent()) {
            return userService.addUser(user);
        } else {
            throw new UserNotFoundException("User not found to be updated.");
        }
    }

    // poc-user-service/user-by-id?userId=2
    @GetMapping(value = "/user-by-id")
    public ResponseEntity<User> findUserById(@RequestParam Integer userId) {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isPresent()) {
            User u = userById.get();
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found with the above mentioned id.");
        }
    }


    // poc-user-service/delete-user-by-id?userId=3
    @GetMapping(value = "/delete-user-by-id")
    public String deleteUserById(@RequestParam Integer userId) {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isPresent()) {
            return userService.deleteUserById(userId);
        } else {
            throw new UserNotFoundException("User not found with the above mentioned id.");
        }
    }

    // poc-user-service/validateuser/user1/password
    @GetMapping(value = "/validateuser/{userName}/{password}")
    public Boolean validateUser(@PathVariable String userName, @PathVariable String password) {
        log.info("User logged in.");
        return userService.findByUserNameAndPassword(userName, password);
    }

    // poc-user-service/validateuser/user1/password/cust
    @GetMapping(value = "/validateuser/{userName}/{password}/{userType}")
    public Boolean validateUser(@PathVariable String userName, @PathVariable String password, @PathVariable String userType) {
        log.info("User logged in.");
        return userService.isUserValid(userName, password, userType);
    }
}
