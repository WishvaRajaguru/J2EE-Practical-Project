package lk.rajaguru.app.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.rajaguru.web.app.core.model.Product;
import lk.rajaguru.web.app.core.model.ProductCategory;
import lk.rajaguru.web.app.core.service.ProductService;

import java.io.IOException;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN","SUPER_ADMIN"}))
@WebServlet("/servlet/product/delete")
public class DeleteProduct extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product_id = request.getParameter("product_id");

        Product product = productService.getProduct(Integer.parseInt(product_id));
        productService.deleteProduct(product);

        response.sendRedirect(request.getContextPath() + "/admin/product_manager.jsp");
    }
}
