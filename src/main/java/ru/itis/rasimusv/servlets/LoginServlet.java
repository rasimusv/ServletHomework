package ru.itis.rasimusv.servlets;

import ru.itis.rasimusv.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String hashPassword = PasswordService.encrypt(request.getParameter("password"));

        String UUID = usersService.getUUIDByCredentials(username, hashPassword);

        if (UUID != null) {
            Cookie cookie = new Cookie("Auth", UUID);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            response.setContentType("text/html");
            PrintWriter out;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            out.println("Invalid");
            try {
                request.getRequestDispatcher("/jsp/students.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
