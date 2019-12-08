<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="registration" var="val"/>
<html>
<head>
    <title> <fmt:message key="label.title" bundle="${val}"/></title>
</head>
<body>
<form name="Registration form" method="post" action="/controller">
    <input type="hidden" name="command" value="registration"/>
    <fmt:message key="field.login" bundle="${val}"/>:<input type="text" name="login" value="${inputLogin}"/> ${incorrectLoginMessage}<br/>
    <fmt:message key="field.password" bundle="${val}"/>: <input type="text" name="password" value=""/> ${incorrectPasswordMessage}<br/>
    <fmt:message key="field.repeatpassword" bundle="${val}"/>: <input type="text" name="repeat_password" value=""/> ${incorrectRepeatPasswordMessage}<br/>
    <fmt:message key="field.email" bundle="${val}"/>:<input type="text" name="email" value="${inputEmail}"/> ${incorrectEmailMessage}<br/>
    <fmt:message key="field.lastname" bundle="${val}"/> :<input type="text" name="last_name" value="${inputLastName}"/> ${incorrectLastNameMessage}<br/>
    <fmt:message key="field.firstname" bundle="${val}"/> :<input type="text" name="first_name" value="${inputFirstName}"/>${incorrectFirstNameMessage}<br/>
${registrationError}<br/>
    <input type="submit" value= <fmt:message key="button.register" bundle="${val}"/> />
</form>
<hr/>
</body>
</html>