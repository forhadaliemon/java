package com.example.filter;

public class AuthorizationFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String servletPath = httpRequest.getServletPath();

        // Allow access to login and registration pages
        if (servletPath.equals("/login.jsp") || servletPath.equals("/register.jsp") || servletPath.equals("/register")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        // If the user has not logged in, redirect to login page
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
    public void destroy() {
    }
}
