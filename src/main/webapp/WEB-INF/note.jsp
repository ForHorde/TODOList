<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/themes/smoothness/jquery-ui.css" />
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js"></script>
    <%--<script type="text/javascript">--%>
        <%--$(function () {--%>
            <%--$("#tblLocations").sortable({--%>
                <%--items: 'tr:not(tr:first-child)',--%>
                <%--cursor: 'pointer',--%>
                <%--axis: 'y',--%>
                <%--dropOnEmpty: false,--%>
                <%--start: function (e, ui) {--%>
                    <%--ui.item.addClass("selected");--%>
                <%--},--%>
                <%--stop: function (e, ui) {--%>
                    <%--ui.item.removeClass("selected");--%>
                    <%--$(this).find("tr").each(function (index) {--%>
                        <%--if (index > 0) {--%>
                            <%--$(this).find("td").eq(2).html(index);--%>
                        <%--}--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>
    <style type="text/css">
        /*table th, table td*/
        /*{*/
            /*width: 100px;*/
            /*padding: 5px;*/
            /*border: 1px solid #ccc;*/
        /*}*/
        /*.selected*/
        /*{*/
            /*background-color: #666;*/
            /*color: #fff;*/
        /*}*/

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
  <tr>
    <th>Notes</th>
  </tr>
  <c:forEach var="note" items="${noteList}" varStatus="i">
   <tr>
       <%--<td>${note.id=i.count}</td>--%>
   <td><a href="/edit/${note.id}"> ${note.text} </td>

    <td><a href="/delete/${note.id}" onclick ="return confirm('Are you sure?');">Delete this item</a></td>
     </tr>
  </c:forEach>
</table>
<a href="/add-new-note">Add new note</a>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>