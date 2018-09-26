<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 30.08.2018
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <title>Авторизация</title>
</head>
<style>
    .form form {
        width: 300px;
        margin: 0 auto;
        padding-top: 20px;
    }
</style>
<body style='margin:50px;'>

<%--<form class="form-horizontal" action="/login" method="post">--%>
    <%--<c:if test="${param.error != null}">--%>
        <%--<p style='color:red'>--%>
            <%--Неверное имя пользователя или пароль!--%>
        <%--</p>--%>
    <%--</c:if>--%>
    <%--<c:if test="${param.logout != null}">--%>
        <%--<p style='color:blue'>--%>
            <%--Вы вышли из системы--%>
        <%--</p>--%>
    <%--</c:if>--%>
    <%--<p>--%>
        <%--<label for="username">Имя</label>--%>
        <%--<input type="text" id="username" name="username"/>--%>
    <%--</p>--%>
    <%--<p>--%>
        <%--<label for="password">Пароль</label>--%>
        <%--<input type="password" id="password" name="password"/>--%>
    <%--</p>--%>
    <%--<input type="hidden"--%>
           <%--name="${_csrf.parameterName}"--%>
           <%--value="${_csrf.token}"/>--%>
    <%--<button type="submit">Войти</button>--%>
<%--</form>--%>
<div class="form">

    <form class="form-horizontal" action="/login" method="POST">
        <c:if test="${param.error != null}">
            <p style='color:red'>
                Неверное имя пользователя или пароль!
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p style='color:blue'>
                Вы вышли из системы
            </p>
        </c:if>
        <div class="form-group">
            <h2>Авторизация</h2>
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">Логин</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Логин" name="username" id="username">
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
                    <button type="submit" class="btn btn-default btn-sm">Войти</button>
                </div>
            </div>
            <a href="/register" method="GET">Регистрация</a>
        </div>
    </form>

</div>

</body>
</html>