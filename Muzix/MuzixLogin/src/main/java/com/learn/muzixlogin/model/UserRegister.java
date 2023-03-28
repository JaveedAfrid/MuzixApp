package com.learn.muzixlogin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "register_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String dob;
    private String gender;
    private Integer age;
    private Long phone;
    private String imageUrl;
}
