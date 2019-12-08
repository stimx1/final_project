<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/customtag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/6/2019
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assignment</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:import url="main.jsp"/>
    <div class="table-wrapper1">
        <div class="table-and-button">
            <div class="but">
        <form action="/controller" method="post">
            <select name="diet_id" size="1">
                <c:forEach var="elem" items="${dietList}">
                    <option value="${elem.id}">${elem.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="command" value="assign_diet"/>
            <input type="hidden" name="redirect" value="/controller?command=get_assignment"/>
            <input type="submit" value="Assign"/>
        </form>
            </div>
            <div class="tab">
    <table border="2">
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
                <td><a href="/controller?command=delete_assigned_diet&diet_id=${elem.id}&redirect=/controller?command=get_assignment">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </div>
        </div>
        <div class="tab">
        <div class="table-and-button">
    <table border="2">
        <caption><b>Exercise</b></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach var="elem" items="${assignedExerciseList}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.name}</td>
                <td>${elem.description}</td>
                <td><a href="/controller?command=delete_assigned_exercise&exercise_id=${elem.id}&redirect=/controller?command=get_assignment">delete</a></td>
            </tr>
        </c:forEach>
    </table>
        </div>
            <div class="but">
    <form action="/controller" method="post">
        <select name="exercise_id" size="1">
            <c:forEach var="elem" items="${exerciseList}">
                <option value="${elem.id}">${elem.name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="assign_exercise"/>>
        <input type="hidden" name="redirect" value="/controller?command=get_assignment"/>
        <input type="submit" value="Assign"/>
    </form>
            </div>
    </div>
    </div>
</body>
</html>
