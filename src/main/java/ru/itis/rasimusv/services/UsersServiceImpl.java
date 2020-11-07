package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    UsersRepository<Long> usersRepository;

    public UsersServiceImpl(UsersRepository<Long> usersRepository) {
        this.usersRepository = usersRepository;
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
    public List<User> getAllUsers(int page, int size) {
        return usersRepository.findAll(page, size);
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
