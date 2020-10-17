package ru.itis.rasimusv.servlets;

import ru.itis.rasimusv.models.*;
import ru.itis.rasimusv.services.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String hashPassword = PasswordService.encrypt(request.getParameter("password"));

        User currentUser = new User(username, hashPassword);

        usersService.addUser(currentUser);

        Cookie cookie = new Cookie("Auth", currentUser.getUuid());
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
