//АУФ!! АУФ!!
package ru.itis.rasimusv.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.rasimusv.services.UsersService;
import ru.itis.rasimusv.services.UsersServiceImpl;

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

        String page = request.getRequestURI().replaceAll("/", "");

        System.out.println(page);

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
