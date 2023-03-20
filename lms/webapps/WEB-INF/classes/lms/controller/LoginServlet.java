package lms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lms.repository.UserRepository;

public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean flag = true;

        if (email == null || email.isEmpty()) {
            req.setAttribute("emailErrMsg", "Please fill up the username");
            flag = false;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordErrMsg", "Please fill up the password");
            flag = false;
        }

        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.login(email, password);
                if (isValid) {
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("email", email);
                    resp.sendRedirect(req.getContextPath() + "/app/home");
                    return;
                }

                req.setAttribute("errMsg", "Login failed...");
            } catch (NamingException var9) {
                logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var9.getMessage());
            } catch (SQLException var10) {
                logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var10.getMessage());
            }
        }

        req.setAttribute("email", email);
        req.setAttribute("password", password);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }
}
