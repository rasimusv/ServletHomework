package ru.itis.rasimusv.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
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
}
