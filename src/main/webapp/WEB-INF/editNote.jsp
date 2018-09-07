<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 23.08.2018
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert text here</title>
</head>
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

<body>
<form action="/edit/${note.id}" method="POST">
    <label>Title</label>
    <%--<input type="text" name="note" value="${note.text}" id="form">--%>
    <textarea rows="10" cols="100" maxlength="100"  name="note">${note.text}</textarea>
    <input type="submit" value="Edit note">
</form>
</body>
</html>