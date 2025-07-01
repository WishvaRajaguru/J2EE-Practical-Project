package lk.rajaguru.app.web.servlet;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
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
import java.util.List;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN","SUPER_ADMIN"}))
@WebServlet("/servlet/product/add")
public class AddProducts extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product_name = request.getParameter("product_name");
        String product_desc = request.getParameter("product_desc");
        String product_category = request.getParameter("product_category");
        String product_qty = request.getParameter("product_qty");
        String product_price = request.getParameter("product_price");

        Product product = new Product(product_name, product_desc, Double.parseDouble(product_price), Integer.parseInt(product_qty), ProductCategory.valueOf(product_category));
        productService.saveProduct(product);

        response.sendRedirect(request.getContextPath() + "/admin/product_manager.jsp");
    }
}
