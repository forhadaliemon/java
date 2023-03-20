package lms.controller;

import lms.repository.UserRepository;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePasswordServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ChangePasswordServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Change Password");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/change_password.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPassword = req.getParameter("current_password");
        String newPassword = req.getParameter("new_password");
        String confirmPassword = req.getParameter("confirm_password");
        boolean flag = true;

        if (currentPassword == null || currentPassword.isEmpty()) {
            req.setAttribute("currentPasswordErrMsg", "Please fill up the current password");
            flag = false;
        }

        if (newPassword == null || newPassword.isEmpty()) {
            req.setAttribute("newPasswordErrMsg", "Please fill up the new password");
            flag = false;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            req.setAttribute("confirmPasswordErrMsg", "Please fill up the confirm password");
            flag = false;
        }

        if (flag) {
            if (! newPassword.equals(confirmPassword)) {
                req.setAttribute("confirmPasswordErrMsg", "Password and confirm password does not match");
                flag = false;
            }
            if (flag) {
                try {
                    HttpSession httpSession = req.getSession();
                    UserRepository userRepository = new UserRepository();
                    boolean res = userRepository.changePassword((String) httpSession.getAttribute("email"), currentPassword, newPassword);
                    if (!res) {
                        req.setAttribute("errMsg", "Password changed...");
                    }
                    else {
                        req.setAttribute("errMsg", "Error while changing password...");
                    }
                } catch (NamingException var9) {
                    logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                    logger.log(Level.SEVERE, var9.getMessage());
                } catch (SQLException var10) {
                    logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                    logger.log(Level.SEVERE, var10.getMessage());
                }
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/change_password.jsp");
        requestDispatcher.forward(req, resp);
    }
}
