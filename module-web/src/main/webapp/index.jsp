<%--
  Created by IntelliJ IDEA.
  User: rwish
  Date: 6/26/2025
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>App | Home</title>
</head>
<body>
    <h1>Home JSP</h1>
    <% if (session.getAttribute("user") == null) {%>
        <a href="${pageContext.servletContext.contextPath}/register.jsp">Register</a>
        <a href="${pageContext.servletContext.contextPath}/login.jsp">Login</a>
    <%}else{%>
        <a href="${pageContext.servletContext.contextPath}">Home</a>
        <a href="${pageContext.servletContext.contextPath}">profile</a>
        <a href="${pageContext.servletContext.contextPath}/servlet/logout">Logout</a>
    <%}%>
</body>
</html>
