package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String getUUIDByCredentials(String username, String password) {
        List<User> users = usersRepository.findByCredentials(username, password);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0).getUuid();
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByUUID(String uuid) {
        return usersRepository.findByUUID(uuid);
    }

    @Override
    public boolean containsUserWithUUID(String uuid) {
        return !usersRepository.findByUUID(uuid).isEmpty();
    }

    @Override
    public String getHashPasswordByUsername(String username) {
        List<User> users = usersRepository.findByUsername(username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0).getHashPassword();
    }

    @Override
    public boolean containsUserWithUsername(String username) {
        return !usersRepository.findByUsername(username).isEmpty();
    }
}
