package ru.itis.rasimusv.servlets;

import ru.itis.rasimusv.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        this.usersService = (UsersService) context.getAttribute("dataSource");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(usersService.getAllUsersByAge(19));
    }
}
