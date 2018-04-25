package ru.netcracker.rest.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Artem on 10.05.2016.
 */
/**
 *
 * @author ars
 */
public class LoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(LoginFilter.class);

    private FilterConfig filterConfig = null;

    private boolean active = false;

    @Override
    public void init (FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        String act = filterConfig.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (!active) {
            log.info("Author not logged in");
            httpRequest.getRequestDispatcher("/#/").forward(servletRequest, servletResponse);
        }
//        chain.doFilter(servletRequest, servletResponse);
    }
    public void destroy()
    {
        this.filterConfig = null;
    }
}
