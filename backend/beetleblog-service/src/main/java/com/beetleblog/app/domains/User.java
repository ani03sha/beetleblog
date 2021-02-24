package com.beetleblog.app.domains;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String profilePic;
    private Set<Article> articles;
}
