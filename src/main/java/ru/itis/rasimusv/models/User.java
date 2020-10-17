package ru.itis.rasimusv.models;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class User {
    private Long id;
    private String uuid;
    private String username;
    private String hashPassword;

    public User(String username, String hashPassword) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.uuid = UUID.randomUUID().toString();
    }

    public User(Long id, String uuid, String username, String hashPassword) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.hashPassword = hashPassword;
    }
}
