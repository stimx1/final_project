<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/4/2019
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="table" var="val"/>
<html>
<head>
    <title>Services</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">
<table border="2">
    <caption><b><fmt:message key="caption.services" bundle="${val}"/></b></caption>
    <tr>
        <th><fmt:message key="th.id" bundle="${val}"/></th>
        <th><fmt:message key="th.name" bundle="${val}"/></th>
        <th><fmt:message key="th.price" bundle="${val}"/></th>
        <th><fmt:message key="th.duration" bundle="${val}"/></th>
        <th><fmt:message key="th.action" bundle="${val}"/></th>
        <c:if test="${ currentUser.role eq 'ADMIN' }">
            <th><fmt:message key="th.admin" bundle="${val}"/></th>
        </c:if>
    </tr>
    <c:forEach var="elem" items="${subscriptions}" >
        <tr>
            <td>${elem.id}</td>
            <td>${elem.name}</td>
            <td>${elem.price}</td>
            <td>${elem.duration}</td>
            <td>
                <a href="/controller?command=buy_subscription&subscriptionPrice=${elem.price}&subscriptionId=${elem.id}&subscriptionDuration=${elem.duration}"><fmt:message key="button.buy" bundle="${val}"/></a>
            </td>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <td><a href="/controller?command=delete_subscription&subscriptionId=${elem.id}"><fmt:message key="button.delete" bundle="${val}"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    <c:if test="${ currentUser.role eq 'ADMIN' }">
        <tr>
            <td>?</td>
            <form id="form-2" method="post" action="/controller">
                <input type="hidden" name="command" value="add_subscription"/>
                <td><input type="text" name="subscriptionName" value=""/></td>
                <td><input type="text" name="subscriptionPrice" value=""/></td>
                <td><input type="text" name="subscriptionDuration" value=""/></td>
                <td><a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;"><fmt:message key="button.add" bundle="${val}"/></a></td>
                <td>&nbsp;</td>
            </form>
        </tr>
    </c:if>
</table>
</div>
</body>
</html>
