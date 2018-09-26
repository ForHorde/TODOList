<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 23.08.2018
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Insert text here</title>
</head>
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

<body>
<c:if test="${enote != null}">
    <p style='color:red'>
        Слишком много символов
    </p>
</c:if>
<form action="/edit/${note.id}" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
    <label>Заголовок</label>
    <input type="text" name="title" value="${note.title}" id="form">
    <%--<input type="text" name="note" value="${note.text}" id="form">--%>
    <c:if test="${enote == null}">
    <textarea rows="10" cols="100" maxlength="100"  name="text">${note.text}</textarea>
    </c:if>
    <c:if test="${enote != null}">
        <textarea rows="10" cols="100" maxlength="100"  name="text">${enote.text}</textarea>
    </c:if>
    <label>Картинка</label>
    <img src="${pic}"/>
    <input type="file" name="picture" accept="image/gif, image/jpeg, image/png" >
    <a href="/delete/picture/${note.id}">Удалить картинку</a>
    <br><input type="submit" value="Сохранить изменения">
</form>
<%--<form action="/delete/picture/${note.id}" method="GET">--%>
<%--<input type="submit" value="Удалить картинку">--%>
<%--</form>--%>
<a href="/">На главную</a>
</body>
</html>