<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 07.09.2018
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
<h1>${note.title}</h1>
<article>${note.text}</article>
<img src="${pic}"/>
</div>
<a href="/edit/${note.id}">Редактировать</a>
<a href="/">На главную</a>
</body>
</html>
