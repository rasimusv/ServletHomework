package ru.itis.rasimusv.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.rasimusv.repositories.StudentsRepository;
import ru.itis.rasimusv.repositories.StudentsRepositoryJdbcImpl;
import ru.itis.rasimusv.repositories.UsersRepository;
import ru.itis.rasimusv.repositories.UsersRepositoryJdbcImpl;
import ru.itis.rasimusv.services.StudentsService;
import ru.itis.rasimusv.services.StudentsServiceImpl;
import ru.itis.rasimusv.services.UsersService;
import ru.itis.rasimusv.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);
        StudentsService studentsService = new StudentsServiceImpl(studentsRepository);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        servletContext.setAttribute("dataSource", dataSource);
        servletContext.setAttribute("studentsService", studentsService);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
