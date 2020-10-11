package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.*;

import java.util.*;

public interface UsersService {
    List<User> getAllUsers();

    List<User> getAllUsersByAge(int age);
}
