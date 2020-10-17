package ru.itis.rasimusv.servlets;

import ru.itis.rasimusv.services.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("usersForJsp", usersService.getAllUsersByAge(19));
        try {
            request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String color = request.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        try {
            response.sendRedirect("/users");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
