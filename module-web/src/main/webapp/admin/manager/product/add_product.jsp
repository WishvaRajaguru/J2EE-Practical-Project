<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lk.rajaguru.web.app.core.model.ProductCategory" %><%--
  Created by IntelliJ IDEA.
  User: rwish
  Date: 7/1/2025
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Add Product</title>
  </head>
  <body>
    <h1>Add Product</h1>
    <form method="POST" action="${pageContext.servletContext.contextPath}/servlet/product/add">
      <table>
        <tr>
          <th>Product Name</th>
          <td><input type="text" name="product_name"></td>
        </tr>
        <tr>
          <th>Product Description</th>
          <td><input type="text" name="product_desc"></td>
        </tr>
        <%
          ProductCategory[] values = ProductCategory.values();
          System.out.println(values[0]);
          pageContext.setAttribute("categories",values);
        %>
        <tr>
          <th>Product Category</th>
          <td>
            <label>
              <select name="product_category">
                <c:forEach var="category" items="${categories}">
                  <option value="${category}">${category}</option>
                </c:forEach>
              </select>
            </label>
          </td>
        </tr>
        <tr>
          <th>Product Qty</th>
          <td><input type="number" name="product_qty"></td>
        </tr>
        <tr>
          <th>Product Price</th>
          <td><input type="number" name="product_price"></td>
        </tr>
        <tr>
          <td><input type="submit" value="Add Product"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
