package lk.rajaguru.app.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.model.UserType;
import lk.rajaguru.web.app.core.service.UserService;

import java.io.IOException;

@WebServlet("/servlet/register")
public class Register extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");

        User user = new User(name, contact, email, password, UserType.USER);
        userService.addUser(user);

        response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
    }
}
