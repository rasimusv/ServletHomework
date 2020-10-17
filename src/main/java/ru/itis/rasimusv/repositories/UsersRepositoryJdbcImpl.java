package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.*;

import javax.sql.*;
import java.util.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM users";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_UUID = "SELECT * FROM users WHERE uuid = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND hashpassword = ?";

    //language=SQL
    private static final String SQL_ADD_USER = "INSERT INTO users (uuid, username, hashpassword) VALUES (?, ?, ?)";

    private final SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getLong("id"),
            row.getString("uuid"),
            row.getString("username"),
            row.getString("hashpassword"));

    @Override
    public void save(User entity) {
        template.execute(SQL_ADD_USER, entity.getUuid(), entity.getUsername(), entity.getHashPassword());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findByUUID(String uuid) {
        return template.query(SQL_FIND_ALL_BY_UUID, userRowMapper, uuid);
    }

    @Override
    public List<User> findByCredentials(String username, String password) {
        return template.query(SQL_FIND_ALL_BY_USERNAME_AND_PASSWORD,userRowMapper, username, password);
    }
}
