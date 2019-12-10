<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 12/3/2019
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="table" var="val"/>
<html>
<head>
    <title>Title</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">
    <table>
        <caption><b><fmt:message key="caption.users" bundle="${val}"/></b></caption>
        <tr>
            <th><fmt:message key="th.id" bundle="${val}"/></th>
            <th><fmt:message key="th.email" bundle="${val}"/></th>
            <th><fmt:message key="th.firstname" bundle="${val}"/></th>
            <th><fmt:message key="th.lastname" bundle="${val}"/></th>
            <th><fmt:message key="th.role" bundle="${val}"/></th>
            <th><fmt:message key="th.action" bundle="${val}"/></th>
            <th><fmt:message key="menu.assignment" bundle="${val}"/></th>
        </tr>
        <c:forEach var="elem" items="${userList}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.email}</td>
                <td>${elem.firstName}</td>
                <td>${elem.lastName}</td>
                <td>${elem.role}</td>
                <td><a href="/controller?command=delete_user&userId=${elem.id}&redirect=/controller?command=get_users" class="button"><fmt:message key="button.delete" bundle="${val}"/></a></td>
                <td>
                    <ul>
                        <li><a href="/controller?command=get_assigned_diet&userId=${elem.id}"><fmt:message key="caption.diet" bundle="${val}"/></a> </li>
                        <li><a href="/controller?command=get_assigned_exercise&userId=${elem.id}"><fmt:message key="caption.exercise" bundle="${val}"/></a> </li>
                    </ul>
                 </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
