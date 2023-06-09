package com.flights.user.service;

import com.flights.user.exception.UserNotFoundException;
import com.flights.user.model.User;
import com.flights.user.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserCrudService {

    @Autowired
    private UserCrudRepository userRepo;

    public ResponseEntity<User> addUser(User user) {
        Optional<User> userById = userRepo.findById(user.getUserId());
        if (userById.isPresent())
            throw new UserNotFoundException("User id previously exists.");
        else {
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    public List<User> allUsers() {
        return (List<User>) userRepo.findAll();
    }

    public User updateUser(User user) {
        Optional<User> userById = userRepo.findById(user.getUserId());
        if (userById.isPresent()) {
            return userRepo.save(user);
        } else {
            throw new UserNotFoundException("User not found to be updated.");
        }
    }

    public ResponseEntity<User> findUserById(Integer userId) {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isPresent()) {
            User u = userById.get();
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found with the above mentioned id.");
        }
    }

    public String deleteUserById(Integer userId) {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isPresent()) {
            userRepo.deleteById(userId);
            return "User Deleted..!!";
        } else {
            throw new UserNotFoundException("User not found with above mentioned id.");
        }
    }

    public Boolean findByUserNameAndPassword(String userName, String password) {
        Optional<User> user = userRepo.findByUserNameAndPassword(userName, password);
        return user.isPresent();
    }

    public Boolean isUserValid(String userName, String password, String userType) {
        Optional<User> user = userRepo.findByUserNameAndPasswordAndUserType(userName, password, userType);
        return user.isPresent();
    }
}
