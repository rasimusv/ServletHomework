package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.*;

import javax.sql.*;
import java.util.*;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public void save(User entity) {

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
        return SimpleJdbcTemplate.query(dataSource, SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByAge(int age) {
        return SimpleJdbcTemplate.query(dataSource, SQL_FIND_ALL_BY_AGE, userRowMapper, age);
    }
}