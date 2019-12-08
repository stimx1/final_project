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
        <caption><b>Users</b></caption>
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>password</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Role</th>
            <th>Action</th>
            <th>Assignment</th>
        </tr>
        <c:forEach var="elem" items="${userList}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.email}</td>
                <td>${elem.pass}</td>
                <td>${elem.firstName}</td>
                <td>${elem.lastName}</td>
                <td>${elem.role}</td>
                <td><a href="/controller?command=delete_user&userId=${elem.id}&redirect=/controller?command=get_users" class="button">Delete</a></td>
                <td>
                    <ul>
                        <li><a href="/controller?command=get_assigned_diet&userId=${elem.id}">Diet</a> </li>
                        <li><a href="/controller?command=get_assigned_exercise&userId=${elem.id}">Exercise</a> </li>
                    </ul>
                 </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
