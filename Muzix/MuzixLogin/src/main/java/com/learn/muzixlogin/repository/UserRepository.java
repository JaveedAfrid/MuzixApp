package com.learn.muzixlogin.repository;

import com.learn.muzixlogin.model.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRegister,String> {

    boolean existsByEmail(String email);
    Optional<UserRegister> getUserByEmail(String email);

    @Query(value = "select fname from UserRegister where email = ?1")
    String getByFname(String email);

    Optional<UserRegister> findByEmail(String email);

    String getByPassword(String email);
}
