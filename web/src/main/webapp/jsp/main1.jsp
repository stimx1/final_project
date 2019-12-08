<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 10/19/2019
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Welcome</title>
    <link href="../css/main-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="tool-bar-header"/>
        Name : <a href="/controller?command=user_account">${currentUser.firstName}</a>
        <div class="ref-wrapper">
            <a class="button" href="/controller?command=get_services">Услуги</a>
        </div>
        <div class="ref-wrapper">
            <a class="button" href="/controller?command=get_instructors">Список тренеров</a>
        </div>
        <div class="ref-wrapper">
            <a class="button" href="/controller?command=get_assignment">Назначения</a>
        </div>
        <div class="ref-wrapper">
            <a class="button" href="">История</a>
        </div>
        <div class="ref-wrapper">
            <c:if test="${ currentUser.role eq 'ADMIN' }"><a class="button" href="/controller?command=admin_panel">Панель администратора</a></c:if>
        </div>
        <div class="ref-wrapper">
            <form method="post" action="/controller" id="form-1">
                <input type="hidden" name="command" value="logout"/>
                <a href="#" class="button" onclick="document.getElementById('form-1').submit(); return false;">Выйти</a>
            </form>
        </div>
</div>

</body>
</html>
