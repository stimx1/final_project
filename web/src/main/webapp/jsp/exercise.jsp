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
        <caption><b><fmt:message key="caption.exercise" bundle="${val}"/></b></caption>
        <tr>
            <th><fmt:message key="th.id" bundle="${val}"/></th>
            <th><fmt:message key="th.name" bundle="${val}"/></th>
            <th><fmt:message key="th.description" bundle="${val}"/></th>
            <th><fmt:message key="th.action" bundle="${val}"/></th>
        </tr>
        <c:forEach var="elem" items="${exerciseList}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.name}</td>
                <td>${elem.description}</td>
                <td><a href="/controller?command=delete_exercise&exerciseId=${elem.id}&exerciseName=${elem.name}&exerciseDescription=${elem.description}" class="button"><fmt:message key="button.delete" bundle="${val}"/></a></td>
            </tr>
        </c:forEach>
        <tr>
            <td>?</td>
            <form id="form-2" action="/controller" method="post">
                <input type="hidden" name="command" value="add_exercise"/>
                <td><input type="text" name="exerciseName" value=""/></td>
                <td><input type="text" name="exerciseDescription" value=""/></td>
                <td><a href="#" class="button"
                       onclick="document.getElementById('form-2').submit(); return false;"><fmt:message key="button.add" bundle="${val}"/></a></td>
            </form>
        </tr>
    </table>
</div>
</body>
</html>
