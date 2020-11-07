package ru.itis.rasimusv.services;


import ru.itis.rasimusv.models.User;

import java.util.List;

public interface UsersService {

    void addUser(User user);

    List<User> getAllUsers();

    List<User> getAllUsersByUUID(String uuid);

    boolean containsUserWithUUID(String uuid);

    String getHashPasswordByUsername(String username);

    boolean containsUserWithUsername(String username);
}
