<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${empty pageContext.request.userPrincipal}">
        <a href="${pageContext.servletContext.contextPath}/register.jsp">Register</a>
        <a href="${pageContext.servletContext.contextPath}/login.jsp">Login</a>
    </c:if>
    <c:if test="${not empty pageContext.request.userPrincipal}">
        <a href="${pageContext.servletContext.contextPath}">Home</a>
        <a href="${pageContext.servletContext.contextPath}">profile</a>
        <a href="${pageContext.servletContext.contextPath}/servlet/logout">Logout</a>
    </c:if>

    <h1>Hello, ${pageContext.request.userPrincipal.name}</h1>
</body>
</html>
