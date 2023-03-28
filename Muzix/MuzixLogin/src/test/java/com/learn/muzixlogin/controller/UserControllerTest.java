package com.learn.muzixlogin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.muzixlogin.controller.UserController;
import com.learn.muzixlogin.exception.UserExistsException;
import com.learn.muzixlogin.exception.UserNotFoundException;
import com.learn.muzixlogin.model.UserCredentials;
import com.learn.muzixlogin.model.UserRegister;
import com.learn.muzixlogin.service.UserServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    public static final String User_Register_Url= "/api/v1/user/register";

    private UserRegister user;

    @MockBean
    private UserServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup(){
        user = new UserRegister(1,"saurabh","dabhade","saurabh@gmail.com","saurabh123","12/02/1997","male",24,987654235L,null);
    }

    @Test
    void givenNewUserDetailsWhenDoesNotExistThenShouldReturnCreatedStatus() throws Exception{

        Mockito.when(service.registerUser(any(UserRegister.class))).thenReturn(user);
        mockMvc.perform(post(User_Register_Url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(user)));
        verify(service).registerUser(any(UserRegister.class));
    }

    @Test
    public void givenNewUserDetailsWhenUserDoesExistsThenShouldReturnConflictStatus() throws Exception {
        Mockito.when(service.registerUser(any(UserRegister.class))).thenThrow(UserExistsException.class);

        mockMvc.perform(post(User_Register_Url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isConflict());
        verify(service).registerUser(any(UserRegister.class));
    }

    @Test
    public void givenUserCredentialsShouldLoginAndReturnToken() throws Exception{
        UserCredentials user1 = new UserCredentials("saurabh@gmail.com","saurabh123");
        Mockito.when(service.authenticateUser(any(UserCredentials.class))).thenReturn(anyString());
        mockMvc.perform(post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user1))
        ).andExpect(status().isOk());
        verify(service).authenticateUser(any());
    }

    @Test
    public void givenUserCredentialsDoesNotExistShouldThrowException() throws Exception{
        UserCredentials user2 = new UserCredentials("someguy@gmail.com","someguy@90");
        Mockito.when(service.authenticateUser(any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user2))
        ).andExpect(status().isNotFound());
        verify(service).authenticateUser(any());
    }

    @Test
    public void givenUserDetailsWhenExistsShouldUpdateUser() throws Exception {
        mockMvc.perform(put("/api/v1/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isCreated());
        verify(service).updateProfile(any(UserRegister.class));
    }


    @Test
    public void givenUserDetailsWhenExistsShouldUpdatePassword() throws Exception {
        mockMvc.perform(put("/api/v1/user/updatePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isOk());
        verify(service).updatePassword(any(UserRegister.class));
    }

    @Test
    public void givenUserEmailExistsShouldReturnUser() throws Exception {
        Mockito.when(service.getProfileDetails(anyString())).thenReturn(any(UserRegister.class));
        mockMvc.perform(get("/api/v1/user/getProfile/saurabh@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isFound());
        verify(service).getProfileDetails(anyString());
    }

    @Test
    public void givenUserEmailDoesNotExistsShouldThrowException() throws Exception {
        Mockito.when(service.getProfileDetails(anyString())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/api/v1/user/getProfile/sabh@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isNotFound());
        verify(service).getProfileDetails(anyString());
    }

    @Test
    public void givenUserEmailWhenExistsShouldReturnPassword() throws Exception {
        Mockito.when(service.getPassword(anyString())).thenReturn(anyString());
        mockMvc.perform(get("/api/v1/user/getPassword/saurabh@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isOk());
        verify(service).getPassword(anyString());
    }

    @Test
    public void givenUserEmailWhenDoesNotExistsShouldThrow() throws Exception {
        Mockito.when(service.getPassword(anyString())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/api/v1/user/getPassword/sabh@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isNotFound());
        verify(service).getPassword(anyString());
    }

    @Test
    public void givenUserShouldSaveImage() throws Exception{

        mockMvc.perform(put("/api/v1/user/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        ).andExpect(status().isCreated());
        verify(service).saveImage(any(UserRegister.class));

    }

}
