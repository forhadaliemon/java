package lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {
    public AuthorizationFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("email") == null) {
            httpSession.invalidate();
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
