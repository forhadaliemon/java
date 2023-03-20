package com.example.controller;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username or password cannot be empty");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        User user = userDao.login(username, password);

        if (user == null) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("index.jsp");
    }
}
