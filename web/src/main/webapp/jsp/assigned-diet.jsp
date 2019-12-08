<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 12/5/2019
  Time: 9:02 PM
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
    <div class="wrapper"/>
    <table>
        <caption><b>Diet</b></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach var="elem" items="${assignedDietList}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.name}</td>
                <td>${elem.description}</td>
                <td><a href="/controller?command=delete_assigned_diet&dietId=${elem.id}&redirect=/controller?command=get_assigned_diet">delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <form action="/controller" method="post">
        <select name="dietId" size="1">
            <c:forEach var="elem" items="${dietList}">
                <option value="${elem.id}">${elem.description}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="assign_diet"/>
        <input type="hidden" name="redirect" value="/controller?command=get_assigned_diet"/>
        <input type="submit" value="Assign"/>
    </form>
    </div>
    </div>
</body>
</html>
