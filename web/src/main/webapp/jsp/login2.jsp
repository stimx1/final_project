<%@ page import="by.epam.web.resource.ConfigurationManager" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="login" var="val"/>
<html><head>
    <title>Login</title>
    <script>
        var form = document.forms["ff"];
        form.addEventListener('submit',validate());
        function validate() {
            alert("OKa");
            var name = document.forms["ff"].getElementsByName("email").value;
                if(name=="a@a"){
                    var div = document.getElementsByTagName('div').elements;
                    for(i=0; i<div.length;i++){
                        div[i].classList.toggle("registration-field-wrapper")
                    }
                    alert('OK');
                    return ;
                }
                return false;
        }
    </script>
    <link href="../css/login-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="dws-wrapper">
    <div class="dws-form">
        <input type="radio" id="tab-1" name="tabs" checked>
        <label class="tab" title="Вкладка 1" for="tab-1">Авторизация</label>

        <input type="radio" id="tab-2" name="tabs">
        <label class="tab" title="Вкладка 2" for="tab-2">Регистрация</label>

        <form method="post" class="tab-form" id="form-1" action="/controller" name="1ff1" onsubmit="validate()">
            <input name="command" type="hidden" value="login"/>
            <input name="email" class="input" type="email" placeholder="Введите E-mail">
            <input name="password" class="input" type="password" placeholder="Введите пароль">
<%--            <a href="#" class="button" onclick="document.getElementById('form-1').submit(); return ;">Войти</a>--%>
            <input class="button" type="submit" value="Войти">
        </form>

        <form method="post" class="tab-form" id="form-2" name="ff" action="/controller" onsubmit="return validate()">
            <input name="command" type="hidden" value="registration"/>
            <div >
                <input name="email" class="input" type="email"  value="${inputEmail}" id="id11">
                <label class="invalid-value-label"><c:if test="${ incorrectEmail eq true}">Incorrect Email</c:if></label>
            </div>
            <div class="registration-field-wrapper">
                <input name="firstName" class="input" type="text" placeholder="Введите свое имя" value="${inputFirstName}">
                <label class="invalid-value-label"><c:if test="${ incorrectFirstName eq true}">Incorrect pass</c:if></label>
            </div>
            <div class="registration-field-wrapper">
            <input name="lastName" class="input" type="text" placeholder="Введите Фамилию" value="${inputLastName}">
                <label class="invalid-value-label"><c:if test="${ incorrectLastName eq true}">Incorrect pass</c:if></label>
            </div>
            <div class="registration-field-wrapper">
                <input name="password" class="input" type="password" placeholder="Введите пароль" value="${inputPassword}">
                <label class="invalid-value-label"><c:if test="${ incorrectPassword eq true}">Incorrect pass</c:if></label>
            </div>
            <div class="registration-field-wrapper">
                <input name="repeatedPassword" class="input" type="password" placeholder="Повторите пароль" value="${inputRepeatedPassword}">
                <label class="invalid-value-label"><c:if test="${ incorrectRepeatedPassword eq true}">Incorrect pass</c:if></label>
            </div>
<%--            <a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;">Регистрация</a>--%>
            <input class="button" type="submit" value="Регистрация">
        </form>
    </div>
</div>

<form name="locale" action="/controller" method="post">
    <select name="language" size="1">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <input type="hidden" name="command" value="change_locale"/>
    <input type="submit" value=<fmt:message key="button.select" bundle="${val}"/> />
</form>
</body></html>
