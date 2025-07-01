<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.rajaguru.web.app.core.service.ProductService" %>
<%@ page import="lk.rajaguru.web.app.core.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rwish
  Date: 7/1/2025
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Products | Browse</title>
  </head>
  <body>
    <h1>Products Browse</h1>

    <%
        try {
            InitialContext context = new InitialContext();
            ProductService service = (ProductService) context.lookup("java:global/module_ear/module-product/ProductSessionBean");
            List<Product> products = service.getProducts();
            pageContext.setAttribute("products",products);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    %>

    <table style="width:100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Description</th>
          <th>Category</th>
          <th>QTY</th>
          <th>Price (Rs.)</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.category.name()}</td>
                <td>${product.quantity}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
