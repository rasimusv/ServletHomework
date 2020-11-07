package ru.itis.rasimusv.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.rasimusv.models.Student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentsRepositoryJdbcTemplateImpl implements StudentsRepository<Long> {

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "SELECT * FROM student WHERE age = :age";

    //language=SQL
    private static final String SQL_ADD_STUDENT = "INSERT INTO student (id, first_name, last_name, age, group_number) VALUES (:id, :first_name, :last_name, :age, :group_number)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Student> studentRowMapper = (row, rowNumber) -> Student.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public StudentsRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Student student) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", student.getId());
        map.addValue("first_name", student.getFirstName());
        map.addValue("last_name", student.getLastName());
        map.addValue("age", student.getAge());
        map.addValue("group_number", student.getGroupNumber());
        jdbcTemplate.update(SQL_ADD_STUDENT, map);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, studentRowMapper);
    }

    @Override
    public List<Student> findAllByAge(int age) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_AGE, Collections.singletonMap("age", age), studentRowMapper);
    }
}