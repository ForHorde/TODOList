<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 30.08.2018
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/register" method="POST">
    <label>Title</label>
    <input type="text" name="name" id="form1">
    <input type="text" name="password" id="form2">
    <input type="submit" value="register">
</form>
<c:if test="${user!=null}">
    User is exist
</c:if>

</body>
</html>
