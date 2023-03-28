package com.learn.muzixlogin.service;

import com.learn.muzixlogin.exception.InvalidCredentialsException;
import com.learn.muzixlogin.exception.UserExistsException;
import com.learn.muzixlogin.exception.UserNotFoundException;
import com.learn.muzixlogin.model.UserCredentials;
import com.learn.muzixlogin.model.UserRegister;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {


    UserRegister registerUser(UserRegister user) throws UserExistsException;

    String authenticateUser(UserCredentials userCredentials) throws UserNotFoundException, InvalidCredentialsException;

    void updateProfile(UserRegister user);

    UserRegister getProfileDetails(String email) throws NotFoundException;

    void updatePassword(UserRegister userRegister) throws NotFoundException;

    String getPassword(String email) throws NotFoundException;

    void saveImage(UserRegister userProfile) throws NotFoundException;

}
