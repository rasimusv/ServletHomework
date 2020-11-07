package ru.itis.rasimusv.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.services.UsersService;
import ru.itis.rasimusv.services.UsersServiceImpl;

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
    public void init(ServletConfig config){
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("springContext");
        usersService = context.getBean("usersService", UsersServiceImpl.class);
        passwordEncoder = context.getBean("passwordEncoder", PasswordEncoder.class);
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
