<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="login" var="val"/>
<fmt:setBundle basename="message" var="value"/>
<html>
<head>
    <title>Login</title>
    <script>

        function validate() {
            var name = document.getElementById("login");
            var registrationForm = document.getElementById('form-2');
            var inputsToValidate = registrationForm.querySelectorAll("div>input");
            var rules = {type: "password",};

            var rules = {
                "email": new RegExp("\\w[\\d\\w]{2,}@\\w+\\.\\w{2,4}"),
                "text": new RegExp("^[A-Z]([a-z]{2,40})$"),
                "password": new RegExp("(^.{6,40})$")
            };

            var valid = true;
            Array.prototype.forEach.call(inputsToValidate, function (input) {
                if (!input.value) {
                    valid = false;
                    //input.classList.add();
                }
            });

            if (!valid) {
                alert("Не все поля заполненны");
                return;
            }

            var personalValid = true;
            Array.prototype.forEach.call(inputsToValidate, function (input) {
                if (!rules[input.type].test(input.value)) {
                    var parent = input.parentElement;
                    parent.classList.add("registration-field-wrapper");

                    var errorLabel = parent.querySelector(".invalid-value-label-clients");
                    errorLabel.classList.remove("invisible");

                    input.value = "";

                    personalValid = false;
                    //input.classList.add();
                }
            });

            var passwordInputs = Array.prototype.filter.call(inputsToValidate, function (input) {
                return input.type === "password";
            })

            var passwordsSame = passwordInputs[0].value === passwordInputs[1].value;
            if(!passwordsSame) {
                Array.prototype.forEach.call(passwordInputs, function (input) {
                    var parent = input.parentElement;
                    parent.classList.add("registration-field-wrapper");

                    var errorLabel = parent.querySelector(".invalid-value-label-clients");
                    errorLabel.classList.remove("invisible");
                })
            }

            personalValid = personalValid && passwordsSame;

            if (personalValid) {
                registrationForm.submit();
            }
        }
    </script>
    <link href="../css/login-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="dws-wrapper">
    <div class="dws-form">
        <input type="radio" id="tab-1" name="tabs" checked>
        <label class="tab" title="Вкладка 1" for="tab-1"><fmt:message key="tab1.name" bundle="${val}"/></label>

        <input type="radio" id="tab-2" name="tabs">
        <label class="tab" title="Вкладка 2" for="tab-2"><fmt:message key="tab2.name" bundle="${val}"/></label>

        <form method="post" class="tab-form" id="form-1" action="/controller">
            <input name="command" type="hidden" value="login"/>
            <input name="email" class="input" type="email" placeholder="<fmt:message key="input.email" bundle="${val}"/>">
            <input name="password" class="input" type="password" placeholder="<fmt:message key="input.password" bundle="${val}"/>">
            <label class="invalid-value-label-clients"><c:if test="${errorLoginPassMessage eq true}"> <fmt:message key="message.loginError" bundle="${val}"/></c:if></label>
            <a href="#" class="button" onclick="document.getElementById('form-1').submit(); return false;"><fmt:message key="button.login" bundle="${val}"/></a>
        </form>

        <form method="post" class="tab-form" id="form-2" action="/controller">
            <input name="command" type="hidden" value="registration"/>
            <div>
                <input name="email" class="input" type="email" value="${inputEmail}" placeholder="<fmt:message key="input.email" bundle="${val}"/>">
                <fmt:bundle basename="message">
                <label class="invisible invalid-value-label-clients"><fmt:message key="message.incorrectEmail" bundle="${value}"/></label>
                <label class="invalid-value-label"><c:if
                        test="${ incorrectEmail eq true}"><fmt:message key="message.incorrectEmail" bundle="${value}"/></c:if></label>
                </fmt:bundle>
            </div>
            <div>
                <input name="firstName" class="input" type="text" placeholder="<fmt:message key="input.firstName" bundle="${val}"/>"
                       value="${inputFirstName}">
                <fmt:bundle basename="message">
                <label class="invisible invalid-value-label-clients"><fmt:message key="message.incorrectFirstName" bundle="${value}"/></label>
                <label class="invalid-value-label"><c:if
                        test="${ incorrectFirstName eq true}"><fmt:message key="message.incorrectFirstName" bundle="${value}"/></c:if></label>
                </fmt:bundle>
            </div>
            <div>
                <input name="lastName" class="input" type="text" placeholder="<fmt:message key="input.lastName" bundle="${val}"/>" value="${inputLastName}">
                <fmt:bundle basename="message">
                <label class="invisible invalid-value-label-clients"><fmt:message key="message.incorrectLastName" bundle="${value}"/></label>
                <label class="invalid-value-label"><c:if
                        test="${ incorrectLastName eq true}"><fmt:message key="message.incorrectLastName" bundle="${value}"/></c:if></label>
            </fmt:bundle>
            </div>
            <div>
                <input name="password" class="input" type="password" placeholder="<fmt:message key="input.password" bundle="${val}"/>"
                       value="${inputPassword}">
                <fmt:bundle basename="message">
                    <label class="invisible invalid-value-label-clients"><fmt:message key="message.incorrectPassword" bundle="${value}"/></label>
                    <label class="invalid-value-label"><c:if
                            test="${ incorrectPassword eq true}"><fmt:message key="message.incorrectPassword" bundle="${value}"/></c:if></label>
                </fmt:bundle>
            </div>
            <div>
                <input name="repeatedPassword" class="input" type="password" placeholder="<fmt:message key="input.repeatPassword" bundle="${val}"/>"
                       value="${inputRepeatedPassword}">
                <fmt:bundle basename="message">
                    <label class="invisible invalid-value-label-clients"><fmt:message key="message.incorrectRepeatPassword" bundle="${value}"/></label>
                    <label class="invalid-value-label"><c:if
                            test="${ incorrectRepeatedPassword eq true}"><fmt:message key="message.incorrectRepeatPassword" bundle="${value}"/></c:if></label>
                </fmt:bundle>
            </div>
            <fmt:bundle basename="message">
            <label class="invalid-value-label-clients"><c:if test="${errorRegistrationMessage eq true}"><fmt:message key="message.registerError" bundle="${value}"/></c:if></label>
            </fmt:bundle>
            <a href="#" class="button" onclick="validate()"><fmt:message key="button.signUp" bundle="${val}"/></a>
        </form>
    </div>
</div>

<form name="locale" action="/controller" method="post">
    <select name="language" size="1">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <input type="hidden" name="command" value="change_locale"/>
    <input type="submit" value=<fmt:message key="button.select" bundle="${val}"/> >
</form>
</body>
</html>
