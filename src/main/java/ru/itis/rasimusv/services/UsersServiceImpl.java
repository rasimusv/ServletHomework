package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.*;
import ru.itis.rasimusv.repositories.*;

import java.util.*;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByAge(int age) {
        return usersRepository.findAllByAge(age);
    }


}
