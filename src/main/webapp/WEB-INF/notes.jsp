<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Insert title here</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/themes/smoothness/jquery-ui.css"/>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js"></script>
    <script type="text/javascript">
        function sort() {
            var id = "";
            $("#tblLocations").find("tr").each(function (index) {
                if (index > 0) {
                    id += "&" + $(this).find("td").eq(2).html() + "color" + $(this).find("td").eq(1).find("input").val();
                }
            });

            $.ajax({
                url: 'sort',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    text: id
                }),
                success: function (data) {
                    location.reload()
                }
            });

        }

        function saveShow() {
            $("#save").show()
        }

        $(function () {
            $("#tblLocations").sortable({
                items: 'tr:not(tr:first-child)',
                cursor: 'pointer',
                axis: 'y',
                dropOnEmpty: false,
                start: function (e, ui) {
                    ui.item.addClass("selected");
                    saveShow()
                },
                stop: function (e, ui) {
                    ui.item.removeClass("selected");
                    // $(this).find("tr").each(function (index) {
                    //     if (index > 0) {
                    //         $(this).find("td").eq(2).html(index);
                    //     }
                    // });
                }
            });
        });

        // function doAjax() {
        //     var table = $("#tblLocations").serialize();
        //
        //
        //     $.ajax({
        //         url: 'sort',
        //         type: 'GET',
        //         dataType: 'json',
        //         contentType: 'application/json',
        //         mimeType: 'application/json',
        //         data: ({
        //             text: table
        //         }),
        //         success: function () {
        //
        //         }
        //     });
        // }


    </script>
    <style type="text/css">
        table th, table td {
            width: 100px;
            padding: 5px;
            border: 1px solid #ccc;
        }

        .selected {
            background-color: #666;
            color: #fff;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;

        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        a.btn:hover {
            -webkit-transform: scale(1.1);
            -moz-transform: scale(1.1);
            -o-transform: scale(1.1);
        }

        a.btn {
            -webkit-transform: scale(0.8);
            -moz-transform: scale(0.8);
            -o-transform: scale(0.8);
            -webkit-transition-duration: 0.5s;
            -moz-transition-duration: 0.5s;
            -o-transition-duration: 0.5s;
        }
    </style>
</head>
<body>
<form>
    <table id="tblLocations">
        <tr>
            <th>Записи</th>
        </tr>
        <c:forEach var="note" items="${noteList}" varStatus="i">
            <tr bgcolor="${note.color}">
                <td><a href="/show/${note.id}"> ${note.title}</a></td>
                <td><input type="color" name="color" value="${note.color}" onclick="saveShow()"></td>
                <td hidden>${note.id}</td>
                <td><a href="/delete/${note.id}" class="btn btn-danger a-btn-slide-text"  onclick="return confirm('Удалить?');">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>
                </a></td>

            </tr>
        </c:forEach>
    </table>
</form>


</div>
<button onclick="sort()" hidden id="save">Сохранить изменения</button>
<br><a href="/add-new-note">Добавить запись</a>
<a href="<c:url value="/logout" />">Выйти</a>
</body>
</body>
</html>