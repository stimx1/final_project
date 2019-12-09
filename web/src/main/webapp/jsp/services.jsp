<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/4/2019
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Services</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">
<table border="2">
    <caption><b>Services</b></caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Duration</th>
        <th>Action</th>
        <c:if test="${ currentUser.role eq 'ADMIN' }">
            <th>Admin action</th>
        </c:if>
    </tr>
    <c:forEach var="elem" items="${subscriptions}" >
        <tr>
            <td>${elem.id}</td>
            <td>${elem.name}</td>
            <td>${elem.price}</td>
            <td>${elem.duration}</td>
            <td>
                <%--<form action="/controller" id="subscription-form${i}" method="post">--%>
                    <%--<input type="hidden" name="command" value="buy_subscription"/>--%>
                    <%--<input type="hidden" name="subscriptionPrice" value="${elem.price}"/>--%>
                    <%--<input type="hidden" name="subscriptionId" value="${elem.id}"/>--%>
                    <%--<input type="hidden" name="subscriptionDuration" value="${elem.duration}"/>--%>
                    <%--<a href="#" class="button" onclick="document.getElementById('subscription-form${i}').submit(); return false;">Buy</a>--%>
                <%--</form>--%>
                <a href="/controller?command=buy_subscription&subscriptionPrice=${elem.price}&subscriptionId=${elem.id}&subscriptionDuration=${elem.duration}">Buy</a>
            </td>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <td><a href="/controller?command=delete_subscription&subscriptionId=${elem.id}">delete</a></td>
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
                <td><a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;">Add</a></td>
                <td>&nbsp;</td>
            </form>
        </tr>
    </c:if>
</table>
</div>
</body>
</html>
