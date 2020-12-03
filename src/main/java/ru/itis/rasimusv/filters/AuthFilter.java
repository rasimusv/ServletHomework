//АУФ!! АУФ!!
package ru.itis.rasimusv.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //05
        /*
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
         */

        String page = request.getRequestURI().replaceAll("/", "");

        if (!page.equals("registration") && !page.equals("login")) {

            HttpSession session = request.getSession(false);

            if (session == null || !session.getAttribute("Authenticated").equals("true")) {
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
