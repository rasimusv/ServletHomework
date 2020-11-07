package ru.itis.rasimusv.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.rasimusv.models.User;

import java.util.*;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<Long> {

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM users";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "SELECT * FROM users ORDER BY id LIMIT :limit OFFSET :offset ;";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_UUID = "SELECT * FROM users WHERE uuid = :uuid";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_USERNAME = "SELECT * FROM users WHERE username = :username";

    //language=SQL
    private static final String SQL_ADD_USER = "INSERT INTO users (uuid, username, hashpassword) VALUES (:uuid, :username, :hashpassword)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> new User(
            row.getLong("id"),
            row.getString("uuid"),
            row.getString("username"),
            row.getString("hashpassword"));

    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("uuid", user.getUuid());
        map.addValue("username", user.getUsername());
        map.addValue("hashpassword", user.getHashPassword());
        jdbcTemplate.update(SQL_ADD_USER, map);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return jdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }


    @Override
    public List<User> findByUUID(String uuid) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_UUID, Collections.singletonMap("uuid", uuid), userRowMapper);
    }

    @Override
    public List<User> findByUsername(String username) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_USERNAME, Collections.singletonMap("username", username), userRowMapper);
    }
}
