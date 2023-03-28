package com.learn.muzixlogin.controller;

import com.learn.muzixlogin.exception.InvalidCredentialsException;
import com.learn.muzixlogin.exception.UserNotFoundException;
import com.learn.muzixlogin.model.UserCredentials;
import com.learn.muzixlogin.model.UserRegister;
import com.learn.muzixlogin.service.UserService;
import javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/v1/user/")
public class UserController {

    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getProfile/{email}")
    public ResponseEntity<?> getUserProfile(@PathVariable String email) throws NotFoundException {
        return new ResponseEntity<>(userService.getProfileDetails(email), HttpStatus.FOUND);
    }

    @PostMapping("register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegister user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentials userCredentials)throws UserNotFoundException, InvalidCredentialsException {
        String s = userService.authenticateUser(userCredentials);
        logger.debug("user {} authenticated successfully", userCredentials.getEmail());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateProfile(@RequestBody UserRegister user)throws UserNotFoundException{
        userService.updateProfile(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UserRegister user) throws NotFoundException {
        userService.updatePassword(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getPassword/{email}")
    public ResponseEntity<?> getPassword(@PathVariable String email) throws NotFoundException {
        return new ResponseEntity<>(userService.getPassword(email),HttpStatus.OK);
    }

    @PutMapping("profile")
    public ResponseEntity<?> setImage(@RequestBody UserRegister userRegister) throws NotFoundException {
        userService.saveImage(userRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
