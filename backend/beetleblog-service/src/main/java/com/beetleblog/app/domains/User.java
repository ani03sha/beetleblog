package com.beetleblog.app.domains;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;

    private String fullName;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String profilePic;
}
