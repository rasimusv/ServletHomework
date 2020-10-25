package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.Student;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class StudentsRepositoryJdbcImpl implements StudentsRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "SELECT * FROM student WHERE age = ?";

    private final SimpleJdbcTemplate template;

    public StudentsRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private final RowMapper<Student> studentRowMapper = row -> Student.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public void save(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return template.query(SQL_FIND_ALL, studentRowMapper);
    }

    @Override
    public List<Student> findAllByAge(int age) {
        return template.query(SQL_FIND_ALL_BY_AGE, studentRowMapper, age);
    }
}
