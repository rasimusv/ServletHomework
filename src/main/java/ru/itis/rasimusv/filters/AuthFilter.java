//АУФ!! АУФ!!

package ru.itis.rasimusv.filters;

import ru.itis.rasimusv.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthFilter implements Filter {

    UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String [] url = request.getRequestURL().toString().split("/");
        String page = url[url.length - 1];

        if (!page.equals("registration") && !page.equals("login")) {
            Cookie [] cookies = request.getCookies();
            Cookie cookie = null;

            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("Auth")) {
                    cookie = cookies[i];
                    break;
                }
            }

            if (cookie == null || !usersService.containsUserWithUUID(cookie.getValue())) {
                response.sendRedirect("/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
