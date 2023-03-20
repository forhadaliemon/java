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

public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Registration");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        boolean flag = true;

        if (email == null || email.isEmpty()) {
            req.setAttribute("emailErrMsg", "Please fill up the username");
            flag = false;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordErrMsg", "Please fill up the password");
            flag = false;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            req.setAttribute("confirmPasswordErrMsg", "Please fill up the confirm password");
            flag = false;
        }

        if (flag) {
            if (! password.equals(confirmPassword)) {
                req.setAttribute("confirmPasswordErrMsg", "Password and confirm password does not match");
                flag = false;
            }
            if (flag) {
                try {
                    UserRepository userRepository = new UserRepository();
                    boolean res = userRepository.register(email, password);
                    if (!res) {
                        req.setAttribute("errMsg", "Registration successful...");
                    }
                    else {
                        req.setAttribute("errMsg", "Registration failed...");
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

        req.setAttribute("email", email);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/registration.jsp");
        requestDispatcher.forward(req, resp);
    }
}
