<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/3/2019
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
    <link href="../css/main-style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="ref-wrapper">
    <a class="button" href="/controller?command=get_diets">Диеты</a>
</div>
<div class="ref-wrapper">
    <a class="button" href="/controller?command=get_exercises">Упражнения</a>
</div>
<div class="ref-wrapper">
    <a class="button" href="/controller?command=get_users">Пользователи</a>
</div>
</body>
</html>
