<%@ page import="by.epam.web.resource.ConfigurationManager" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="login" var="val"/>
<html><head>
    <title>Login</title>
    <script>
        function validate() {
            var name = document.getElementById("login");
            if(!name.value){
                name.style.border = "2px solid red";
                return false;
            }
            return true;
        }
    </script>
    <link href="../css/login-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<lol id="test">
<form name="loginForm" method="POST" action="/controller" onsubmit="return validate();">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="field.login" bundle="${val}"/>:<br/>
    <span id="em"></span>
    <input type="text" id="login" name="login" value="" /><br/>
    <fmt:message key="field.password" bundle="${val}"/>:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value= <fmt:message key="button.login" bundle="${val}"/> />
</form>

<form name="register" method="post" action="jsp/registration.jsp">
    <input type="submit" name="command" value= <fmt:message key="button.registration" bundle="${val}"/> />
</form>
</lol>

<form name="locale" action="/controller" method="post">
    <select name="language" size="1">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <input type="hidden" name="command" value="change_locale"/>
    <input type="submit" value=<fmt:message key="button.select" bundle="${val}"/> />
</form>
</body></html>
