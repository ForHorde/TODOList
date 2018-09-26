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
<style>
    .form form {
        width: 300px;
        margin: 0 auto;
        padding-top: 20px;
    }
</style>
<head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<%--<form action="/register" method="POST">--%>
    <%--<label>Имя</label>--%>
    <%--<input type="text" name="name" id="form1">--%>
    <%--<label>Пароль</label>--%>
    <%--<input type="password" name="password" id="form2">--%>
    <%--<input type="submit" value="register">--%>
<%--</form>--%>

<div class="form">

    <form class="form-horizontal" action="/register" method="POST">
        <c:if test="${user!=null}">
            Пользователь с таким именем уже существует!
        </c:if>
        <c:if test="${error!=null}">
            ${error}
        </c:if>
        <div class="form-group">
            <h2>Регистрация</h2>
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">Логин</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Логин" name="name" id="username">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Пароль</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" placeholder="Пароль" name="password" id="password">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default btn-sm">Зарегистрироваться</button>
                </div>
            </div>

        </div>
    </form>

</div>
</body>
</html>
