<%--
  Created by IntelliJ IDEA.
  User: KBelkin
  Date: 22.08.2018
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert text here</title>
</head>
<body>
<form action="/add-new-note" method="POST">
    <label>Title</label>
    <%--<input type="textarea" name="note" id="form">--%>
    <textarea rows="10" cols="100" maxlength="100"  name="note"></textarea>
    <input type="submit" value="Add new note">
</form>
</body>
</html>