package lk.rajaguru.app.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rajaguru.web.app.core.mail.VerificationMail;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.model.UserType;
import lk.rajaguru.web.app.core.provider.MailServiceProvider;
import lk.rajaguru.web.app.core.service.UserService;
import lk.rajaguru.web.app.core.util.Encryption;

import java.io.IOException;
import java.util.UUID;

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

        User user = new User(name, contact, email, Encryption.encrypt(password), UserType.USER);
        userService.addUser(user);

        MailServiceProvider.getInstance().queueMailer(new VerificationMail(email, UUID.randomUUID().toString()));

        response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
    }
}
