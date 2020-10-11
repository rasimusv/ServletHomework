package ru.itis.rasimusv.servlets;


import ru.itis.rasimusv.repositories.*;
import ru.itis.rasimusv.services.*;

import com.zaxxer.hikari.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/JDBC_Work");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");
        hikariConfig.setMaximumPoolSize(10);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(usersService.getAllUsersByAge(19));
    }
}
