<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 12/10/2019
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="table" var="val"/>
<html>
<head>
    <title>Coaching room</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">
    <table border="2">
        <caption><b><fmt:message key="caption.selected" bundle="${val}"/></b></caption>
        <tr>
            <th><fmt:message key="th.firstname" bundle="${val}"/></th>
            <th><fmt:message key="th.lastname" bundle="${val}"/></th>
            <th><fmt:message key="th.userid" bundle="${val}"/></th>
        </tr>
        <c:forEach var="elem" items="${instructors}">
            <tr>
                <c:if test="${elem.id != 0}">
                    <td>${elem.lastName}</td>
                    <td>${elem.firstName}</td>
                    <td>${elem.id}</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
