/*
package ru.itis.rasimusv.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.rasimusv.repositories.StudentsRepository;
import ru.itis.rasimusv.repositories.StudentsRepositoryJdbcTemplateImpl;
import ru.itis.rasimusv.repositories.UsersRepository;
import ru.itis.rasimusv.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.rasimusv.services.StudentsService;
import ru.itis.rasimusv.services.StudentsServiceImpl;
import ru.itis.rasimusv.services.UsersService;
import ru.itis.rasimusv.services.UsersServiceImpl;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "ru.itis.rasimusv")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public UsersRepository<Long> usersRepository() {
        return new UsersRepositoryJdbcTemplateImpl(jdbcTemplate());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository());
    }

    @Bean
    public StudentsRepository<Long> studentsRepository() {
        return new StudentsRepositoryJdbcTemplateImpl(jdbcTemplate());
    }

    @Bean
    public StudentsService studentsService() {
        return new StudentsServiceImpl(studentsRepository());
    }

}
*/
