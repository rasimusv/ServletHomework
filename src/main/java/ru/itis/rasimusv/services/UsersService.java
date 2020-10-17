package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.*;

import java.util.*;

public interface UsersService {
    
    String getUUIDByCredentials(String username, String password);

    void addUser(User user);

    List<User> getAllUsers();

    List<User> getAllUsersByUUID(String uuid);

    boolean containsUserWithUUID(String uuid);
}
