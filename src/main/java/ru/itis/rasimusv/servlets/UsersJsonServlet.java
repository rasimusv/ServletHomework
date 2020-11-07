package ru.itis.rasimusv.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@WebServlet("/json/users")
public class UsersJsonServlet extends HttpServlet {

    private UsersService usersService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.objectMapper = (ObjectMapper) servletContext.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        String size = req.getParameter("size");

        List<User> users = usersService.getAllUsers(Integer.parseInt(page),
                Integer.parseInt(size));

        String response = objectMapper.writeValueAsString(users);
        resp.getWriter().write(response);
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reader reader = req.getReader();
        User json = objectMapper.readValue(reader, User.class);
        usersService.addUser(json);
    }
}
