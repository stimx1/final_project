<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/4/2019
  Time: 7:35 PM
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
    <table border="2">
        <caption><b><fmt:message key="caption.instructors" bundle="${val}"/></b></caption>
        <tr>
            <th><fmt:message key="th.id" bundle="${val}"/></th>
            <th><fmt:message key="th.firstname" bundle="${val}"/></th>
            <th><fmt:message key="th.lastname" bundle="${val}"/></th>
            <th><fmt:message key="th.info" bundle="${val}"/></th>
            <th><fmt:message key="th.selected" bundle="${val}"/></th>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <td><fmt:message key="th.action" bundle="${val}"/></td>
            </c:if>
        </tr>
        <c:forEach var="elem" items="${instructors}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.firstName}</td>
                <td>${elem.lastName}</td>
                <td>${elem.info}</td>
                <td>
                    <c:choose>
                        <c:when test="${ selectedId eq elem.id }">
                            <input type="radio" name="instructorId" value="${elem.id}" checked/>
                        </c:when>
                        <c:otherwise>
                            <form action="/controller" method="post" id="form-1" >
                                <input type="radio" name="instructorId" value="${elem.id}" onchange="this.form.submit()"/>
                                <input type="hidden" name="command" value="change_instructor"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:if test="${ currentUser.role eq 'ADMIN' }">
                    <td><a href="/controller?command=delete_instructor&instructorId=${elem.id}&firstName=${elem.firstName}&lastName=${elem.lastName}&info=${elem.info}" ><fmt:message key="button.delete" bundle="${val}"/></a></td>
                </c:if>
            </tr>
        </c:forEach>

        <c:if test="${ currentUser.role eq 'ADMIN' }">
            <tr>
                <td>?</td>
                <form id="form-2" method="post" action="/controller">
                    <input type="hidden" name="command" value="add_instructor"/>
                    <td><input type="text" name="firstName" value="" pattern="^[A-Z][a-z]{2,20}$"/></td>
                    <td><input type="text" name="lastName" value="" pattern="^[A-Z][a-z]{2,20}$"/></td>
                    <td><input type="text" name="info" value="" pattern="^[A-Z]([a-z]|\d){2,50}$"/></td>
                    <td>&nbsp;</td>
<%--                    <td><a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;"><fmt:message key="button.add" bundle="${val}"/></a></td>--%>
                    <td><input type="submit" value="<fmt:message key="button.add" bundle="${val}"/>"> </td>
                </form>
            </tr>
        </c:if>

    </table>



</div>
</body>
</html>
