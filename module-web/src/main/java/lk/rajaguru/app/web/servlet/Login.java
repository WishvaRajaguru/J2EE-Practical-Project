package lk.rajaguru.app.web.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.service.UserService;
import lk.rajaguru.web.app.core.util.Encryption;

import java.io.IOException;

@WebServlet("/servlet/login")
public class Login extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthenticationStatus status = securityContext.authenticate(request, response, new AuthenticationParameters().credential(new UsernamePasswordCredential(email, Encryption.encrypt(password))));

        System.out.println(status);

        if (status == AuthenticationStatus.SUCCESS) {
            response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");
        }else{
            response.getWriter().write("Invalid email or password");
        }
    }
}
