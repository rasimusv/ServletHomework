package ru.itis.rasimusv.servlets;

import ru.itis.rasimusv.services.StudentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    private StudentsService studentsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.studentsService = (StudentsService) servletContext.getAttribute("studentsService");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("studentsForJsp", studentsService.getAllStudents());
        try {
            request.getRequestDispatcher("/jsp/students.jsp").forward(request, response);
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
            response.sendRedirect("/students");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
