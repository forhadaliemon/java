package com.example.controller;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }

        if (!userDao.checkPassword(user.getUsername(), oldPassword)) {
            request.setAttribute("errorMessage", "Old password is incorrect");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }

        userDao.changePassword(user.getUsername(), newPassword);
        response.sendRedirect("index.jsp");
    }
}
