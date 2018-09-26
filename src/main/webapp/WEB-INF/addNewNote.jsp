<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 22.08.2018
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<html>
<head>
    <title>Insert text here</title>
</head>
<body>
<c:if test="${enote != null}">
    <p style='color:red'>
        Слишком много символов
    </p>
</c:if>
<form action="/add-new-note" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
    <label>Заголовок</label>
    <input type="text" name="title" value="${note.title}" id="form">
    <%--<input type="text" name="note" value="${note.text}" id="form">--%>
    <textarea rows="10" cols="100" maxlength="100" name="text">${enote.text}</textarea>
    <label>Картинка</label>
    <input type="file" name="picture" accept="image/gif, image/jpeg, image/png" >
    <input type="submit" value="Добавить запись">


</form>
<a href="/">На главную</a>
</body>
</html>