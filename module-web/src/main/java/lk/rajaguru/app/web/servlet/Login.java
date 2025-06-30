package lk.rajaguru.app.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.service.UserService;

import java.io.IOException;

@WebServlet("/servlet/login")
public class Login extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");
        }else{
            response.getWriter().write("Invalid email or password");
        }
    }
}
