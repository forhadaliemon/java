package com.example.controller;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if (userDao.register(user)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Username is already taken");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}
