<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.rajaguru.web.app.core.service.ProductService" %>
<%@ page import="lk.rajaguru.web.app.core.model.Product" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rwish
  Date: 7/1/2025
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product Manager | Admin</title>
  </head>
  <body>
    <h1>Product Manager</h1>
    <hr/>
    <a href="${pageContext.servletContext.contextPath}/admin/manager/product/add_product.jsp">Add Product</a>
    <hr/>
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
            <th>Action</th>
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
                <td>
                    <form action="${pageContext.servletContext.contextPath}/servlet/product/delete" method="post">
                        <input type="hidden" name="product_id" value="${product.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
      </tbody>
    </table>

  </body>
</html>
