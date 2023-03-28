package com.learn.muzixlogin.repository;


import com.learn.muzixlogin.model.UserRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserProfileRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setup(){
        UserRegister user1 = new UserRegister(0,"sam","dabhade","sam@gmail.com","sam123","12/02/1997","Male",24,123L,null);
        repository.save(user1);
    }
    @Test
    public void givenUserExistsinDBThenShouldReturnTrue() {
        assertTrue(repository.existsByEmail("sam@gmail.com"), "User does not exist in Database");
    }

    @Test
    public void givenUserDoesNotExistsinDBThenShouldReturnFalse() {
        assertFalse(repository.existsByEmail("john@mail.com"), "User does not exist in Database");
    }

    @Test
    public void givenUserEmailWhenUserExistsThenReturnOptionalWithUser() {
        Optional<UserRegister> optionalUser = repository.getUserByEmail("sam@gmail.com");
        assertTrue(optionalUser.isPresent());
        UserRegister user = optionalUser.get();
        assertEquals("sam", user.getFname());
    }

    @Test
    public void givenUserEmailWhenUserDoesntExistThenReturnEmptyOptional() {
        Optional<UserRegister> optionalUser = repository.getUserByEmail("john@mail.com");
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void givenUserEmailShouldReturnFname(){
        String byFname = repository.getByFname("sam@gmail.com");
        assertEquals("sam",byFname);
    }

    @Test
    public void givenUserEmailWhenNotExistsShouldReturnNull(){
        String byFname = repository.getByFname("sammmy@gmail.com");
        assertEquals(null,byFname);
    }

    @Test
    public void givenUserEmailWhenExistsShouldReturnUser(){
        Optional<UserRegister> userByEmail = repository.getUserByEmail("sam@gmail.com");
        assertTrue(userByEmail.isPresent());
    }

    @Test
    public void givenUserEmailWhenDoesNotExistsShouldReturnEmpty(){
        Optional<UserRegister> userByEmail = repository.getUserByEmail("sammmy@gmail.com");
        assertTrue(userByEmail.isEmpty());
    }

    @Test
    public void givenUserEmailWhenDoesNotExistsShouldReturnNull(){
        String byPassword = repository.getByPassword("sammy@gmail.com");
        assertEquals(null,byPassword);
    }


}
