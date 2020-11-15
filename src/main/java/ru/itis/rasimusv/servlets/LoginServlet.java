package ru.itis.rasimusv.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.rasimusv.services.UsersService;
import ru.itis.rasimusv.services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config){
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("springContext");
        usersService = context.getBean(UsersServiceImpl.class);
        passwordEncoder = context.getBean(PasswordEncoder.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("Authenticated", "false");
        try {
            //request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            request.getRequestDispatcher("/ftlh/login.ftlh").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashPassword = usersService.getHashPasswordByUsername(username);

        boolean correctPassword = false;

        if (hashPassword != null) {
            correctPassword = passwordEncoder.matches(password, hashPassword);
        }

        if (correctPassword) {
            request.getSession().setAttribute("Authenticated", "true");

            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            try {
                //request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
                request.getRequestDispatcher("/ftlh/login.ftlh").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
