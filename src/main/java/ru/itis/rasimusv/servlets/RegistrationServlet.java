package ru.itis.rasimusv.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("Authenticated", "false");
        try {
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashPassword = passwordEncoder.encode(password);

        if (usersService.containsUserWithUsername(username)) {
            try {
                request.getRequestDispatcher("/jsp/registration.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            User currentUser = new User(username, hashPassword);

            usersService.addUser(currentUser);

            request.getSession().setAttribute("Authenticated", "true");

            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
