<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 10/19/2019
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="main" var="val"/>

<html>
<head>
    <title>Welcome</title>
    <link href="../css/main-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <ul id="navigation">
        <li><a href="/controller?command=user_account"><fmt:message key="menu.account" bundle="${val}"/></a></li>
        <li><a  href="/controller?command=get_services"><fmt:message key="menu.services" bundle="${val}"/></a></li>
            <li><a  href="/controller?command=get_instructors"><fmt:message key="menu.instructors" bundle="${val}"/></a></li>
            <c:if test="${ currentUser.role != 'ADMIN'}">
                <li class="sub">
                    <a  href="#"><fmt:message key="menu.assignment" bundle="${val}"/></a>
                    <ul>
                        <li><a href="/controller?command=get_assigned_diet"><fmt:message key="submenu.diets" bundle="${val}"/></a></li>
                        <li><a href="/controller?command=get_assigned_exercise"><fmt:message key="submenu.exercises" bundle="${val}"/></a></li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <li class="sub">
                    <a  href="#"><fmt:message key="menu.admin" bundle="${val}"/></a>
                    <ul>
                        <li><a href="/controller?command=get_diets"><fmt:message key="submenu.diets" bundle="${val}"/></a></li>
                        <li><a href="/controller?command=get_exercises"><fmt:message key="submenu.exercises" bundle="${val}"/></a></li>
                        <li><a href="/controller?command=get_users"><fmt:message key="submenu.users" bundle="${val}"/></a></li>
                        <li><a href="/controller?command=coaching_room"><fmt:message key="submenu.coaching" bundle="${val}"/></a></li>
                    </ul>
                </li>
            </c:if>

        <li><form method="post" action="/controller" id="form">
                <input type="hidden" name="command" value="logout"/>
            <a href="#" onclick="document.getElementById('form').submit(); return false;"><fmt:message key="button.exit" bundle="${val}"/></a>
            </form>
        </li>
    </ul>

</body>
</html>
