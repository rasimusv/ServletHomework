package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.User;

import java.util.List;

public interface UsersRepository<ID> extends CrudRepository<User, ID> {
    List<User> findByUUID(String uuid);
    List<User> findByUsername(String username);
}
