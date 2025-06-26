<%--
  Created by IntelliJ IDEA.
  User: rwish
  Date: 6/26/2025
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>App | Login</title>
</head>
<body>
  <h1>Login</h1>
  <form action="${pageContext.servletContext.contextPath}/servlet/login" method="post">
      <table>
          <tr>
              <th>Email:</th>
              <td><input type="email" name="email" /></td>
          </tr>
          <tr>
              <th>Password:</th>
              <td><input type="password" name="password" /></td>
          </tr>
          <tr>
              <td><input type="submit" name="Login"/></td>
          </tr>
      </table>
  </form>
</body>
</html>
