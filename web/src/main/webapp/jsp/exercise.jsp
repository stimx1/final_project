<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 12/3/2019
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">
    <table>
        <caption><b>Exercise</b></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach var="elem" items="${exerciseList}" varStatus="i">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.name}</td>
                <td>${elem.description}</td>
                <td><a href="/controller?command=delete_exercise&exerciseId=${elem.id}&redirect=/controller?command=get_exercises" class="button">Delete</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td>?</td>
            <form id="form-2">
                <input type="hidden" name="command" value="add_exercise"/>
                <input type="hidden" name="redirect" value="/controller?command=get_exercises">
                <td><input type="text" name="exerciseName" value=""/></td>
                <td><input type="text" name="exerciseDescription" value=""/></td>
                <td><a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;">Add</a></td>
            </form>
        </tr>
    </table>
</div>
</body>
</html>
