package com.learn.muzixlogin.service;


import com.learn.muzixlogin.exception.InvalidCredentialsException;
import com.learn.muzixlogin.exception.UserExistsException;
import com.learn.muzixlogin.exception.UserNotFoundException;
import com.learn.muzixlogin.model.UserCredentials;
import com.learn.muzixlogin.model.UserRegister;
import com.learn.muzixlogin.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {
    private UserRegister register;
    private UserCredentials credentials;
    @Mock
    private UserRepository repository;
    @Mock
    private JwtGeneratorService jwtGeneratorService;
    @InjectMocks
    private UserServiceImpl service;
    @BeforeEach
    public void setup(){
        register = new UserRegister(1,"saurabh","dabhade","saurabh@gmail.com","pass123","12/02/1997","Male",24,123L,null);
        credentials = new UserCredentials("sam@gmail.com","sam123");
    }
    @Test
    public void givenUserDetailsWhenUserDoesNotExistThenReturnSaveUser() throws UserExistsException {

        when(repository.existsByEmail("saurabh@gmail.com")).thenReturn(false);
        when(repository.save(any())).thenReturn(register);
        UserRegister user = service.registerUser(register);
        assertAll(
                ()->{assertNotNull(user);},
                ()->{assertTrue(user.getEmail().equals("saurabh@gmail.com"));},
                ()->{assertTrue(user.getFname().equals("saurabh"));}

        );
        verify(repository,atLeastOnce()).existsByEmail(anyString());
        verify(repository,times(1)).save(any(UserRegister.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void givenUserDetailWhenUserExistThenThrowException(){

        when(repository.existsByEmail("saurabh@gmail.com")).thenReturn(true);
        assertThrows(UserExistsException.class,() ->service.registerUser(register));
        verify(repository).existsByEmail(anyString());

    }

    @Test
    public void givenUserCredentialsWhenValidThenReturnTrue() throws UserNotFoundException, InvalidCredentialsException {
        //test setup using mock
        when(jwtGeneratorService.generateToken(anyString())).thenReturn("token");
        when(repository.getUserByEmail(anyString()))
                .thenReturn(
                        Optional.of(new UserRegister(1,"saurabh","dabhade","saurabh@gmail.com","sam123","12/02/1997","Male",24,123L,null)));
        //actual test
        assertTrue(service.authenticateUser(credentials).equals("token"));
        verify(repository).getUserByEmail(anyString());
    }


    @Test
    public void givenUserCredentialWhenDoesNotExistThenThrowException(){

        when(repository.getUserByEmail(anyString()))
                .thenReturn(
                        Optional.empty()
                );
        assertThrows(UserNotFoundException.class,()->service.authenticateUser(credentials));
        verify(repository).getUserByEmail(anyString());
    }

    @Test
    public void givenUserShouldUpdateProfile(){
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(register));
        UserRegister userRegister = repository.findByEmail(register.getEmail()).get();
        service.updateProfile(register);
        assertAll(
                ()->{assertNotNull(userRegister);},
                ()->{assertEquals(register,userRegister);}
        );
        verify(repository,times(3)).findByEmail(anyString());
    }

    @Test
    public void givenEmailWhenUserExistsShouldReturnUser() throws NotFoundException {
        when(repository.existsByEmail(anyString())).thenReturn(true);
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(register));
        UserRegister profileDetails = service.getProfileDetails(register.getEmail());
        assertAll(
                ()->{assertNotNull(profileDetails);},
                ()->{assertEquals(register,profileDetails);}
        );
        verify(repository).existsByEmail(anyString());
        verify(repository).findByEmail(anyString());
    }

    @Test
    public void givenEmailWhenUserDoesNotExistsShouldThrowException() throws NotFoundException {
        when(repository.findByEmail("saurabh@gmail.com")).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class,()->service.getProfileDetails("saurabh@gmail.com"));
        verify(repository).existsByEmail("saurabh@gmail.com");
    }


    @Test
    public void givenUserEmailExistsShouldReturnPassword() throws NotFoundException {
        when(repository.existsByEmail("saurabh@gmail.com")).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class,()->service.getPassword("saurabh@gmail.com"));
        assertNotNull(register.getPassword());
        verify(repository).existsByEmail(anyString());
    }

    @Test
    public void givenUserEmailDoesNotExistsShouldThrowException() throws NotFoundException{
        when(repository.existsByEmail(anyString())).thenReturn(false);

        assertThrows(NotFoundException.class,()->service.getPassword("sammy@gmail.com"));
        verify(repository).existsByEmail(anyString());
    }

    @Test
    public void givenUserImageDoesExist(){
        when(repository.existsByEmail(anyString())).thenReturn(false);
        UserRegister userRegister = new UserRegister(1, null, null, null, null, null, null, null, null, null);
        assertThrows(NotFoundException.class,()->service.saveImage(userRegister));
        verify(repository).existsByEmail(null);
    }

    @Test
    public void givenUserDoesNotExistShouldThrowException() throws NotFoundException {
        when(repository.findByEmail(anyString())).thenReturn(
                Optional.of(register)
        );
//        service.saveImage(register);
        assertThrows(NotFoundException.class,()->service.saveImage(register));
        Optional<UserRegister> byEmail = repository.findByEmail(register.getEmail());
        assertTrue(byEmail.isPresent());
        verify(repository).findByEmail(anyString());
    }
}