package com.learn.muzixlogin.service;

import com.learn.muzixlogin.exception.InvalidCredentialsException;
import com.learn.muzixlogin.exception.UserExistsException;
import com.learn.muzixlogin.exception.UserNotFoundException;
import com.learn.muzixlogin.model.UserCredentials;
import com.learn.muzixlogin.model.UserRegister;
import com.learn.muzixlogin.repository.UserRepository;
import javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;
    private JwtGeneratorService jwtGeneratorService;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    public UserServiceImpl(UserRepository repository, JwtGeneratorService jwtGeneratorService) {
        this.repository = repository;
        this.jwtGeneratorService = jwtGeneratorService;
    }

    @Override
    public UserRegister registerUser(UserRegister user) {
        if(repository.existsByEmail(user.getEmail())){
            logger.error("User Already exists With Email {}",user.getEmail());
            throw new UserExistsException("User Already exists With Email");
        }
        logger.info("user register Successfully with email {}",user.getEmail());
        return repository.save(user);
    }

    @Override
    public String authenticateUser(UserCredentials userCredentials) throws UserNotFoundException, InvalidCredentialsException {

        logger.debug("Accessing database for getting user credentials");
        Optional<UserRegister> userByEmail = repository.getUserByEmail(userCredentials.getEmail());
        if(userByEmail.isEmpty()){
            logger.error("User not found with email : {}", userCredentials.getEmail());
            throw new UserNotFoundException("Incorrect Email");
        }
        UserRegister user = userByEmail.get();
        if(user.getPassword().equals(userCredentials.getPassword())){
            logger.info("User authenticated successfully");
            String token = jwtGeneratorService.generateToken(userCredentials.getEmail());
            return token;
        }else {
            logger.error("Password mismatch for user with email : {}", userCredentials.getEmail());
            throw new InvalidCredentialsException("Incorrect password");
        }
    }

    @Override
    public void updateProfile(UserRegister user) {
        Optional byId = repository.findByEmail(user.getEmail());
        if(byId.isPresent()){
            UserRegister userRegister = repository.findByEmail(user.getEmail()).get();
            userRegister.setFname(user.getFname());
            userRegister.setLname(user.getLname());
            userRegister.setAge(user.getAge());
            userRegister.setGender(user.getGender());
            userRegister.setPhone(user.getPhone());
            userRegister.setDob(user.getDob());
            repository.save(userRegister);
        }

    }

    @Override
    public UserRegister getProfileDetails(String email) throws NotFoundException{
        if (repository.existsByEmail(email)){
            Optional<UserRegister> byEmail = repository.findByEmail(email);
            return byEmail.get();
        }
        else {
            throw new UserNotFoundException("user not found");
        }
    }

    @Override
    public void updatePassword(UserRegister user) throws NotFoundException {
        if(repository.existsByEmail(user.getEmail())){
            UserRegister userRegister = repository.findByEmail(user.getEmail()).get();
            userRegister.setPassword(user.getPassword());
            repository.save(userRegister);
        }else{
            throw new NotFoundException("User Not Found");
        }
    }

    @Override
    public String getPassword(String email) throws NotFoundException {
        String password;
        if(repository.existsByEmail(email)){
            UserRegister userRegister = repository.findByEmail(email).get();
            password = userRegister.getPassword();
            return password;
        }else {
            throw new NotFoundException("User Not Found");
        }
    }

    @Override
    public void saveImage(UserRegister userProfile) throws NotFoundException {
        if(repository.existsByEmail(userProfile.getEmail())){
            UserRegister userRegister = repository.findByEmail(userProfile.getEmail()).get();
            userRegister.setImageUrl(userProfile.getImageUrl());
            repository.save(userRegister);
        }else {
            throw new NotFoundException("User Not Found");
        }
    }

}
