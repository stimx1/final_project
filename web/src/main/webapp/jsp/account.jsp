<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/24/2019
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="account" var="val"/>
<fmt:setBundle basename="table" var="value"/>
<fmt:setBundle basename="message" var="message"/>
<head>
    <title>User account</title>
    <link href="../css/account-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<fmt:bundle basename="account">
<div class="main-wrapper">
    <div class="two-form-wrapper">
        <div class="form-wrapper">
            <form action="/controller" id="form-1">
                <label class="caption"><fmt:message key="label.title" bundle="${val}"/></label>
                <div class="field">
                    <label><fmt:message key="label.firstName" bundle="${val}"/>:</label>
                    <input type="text" name="firstName" value="${currentUser.firstName}"/>
                    <fmt:bundle basename="message">
                        <c:if test="${ incorrectFirstName eq true}"><fmt:message key="message.incorrectFirstName" bundle="${message}"/></c:if>
                    </fmt:bundle>
                </div>
                <div class="field">
                    <label><fmt:message key="label.lastName" bundle="${val}"/>:</label>
                    <input type="text" name="lastName" value="${currentUser.lastName}"/>
                    <fmt:bundle basename="message">
                        <c:if test="${ incorrectLastName eq true}"><fmt:message key="message.incorrectLastName" bundle="${message}"/></c:if>
                    </fmt:bundle>
                </div>
                <div class="field">
                    <label><fmt:message key="label.password" bundle="${val}"/>: </label>
                    <input type="password" name="password" value=""/>
                    <fmt:bundle basename="message">
                        <c:if test="${ incorrectPassword eq true}"><fmt:message key="message.incorrectPassword" bundle="${message}"/></c:if>
                    </fmt:bundle>
                </div>
                <div class="field">
                    <label><fmt:message key="label.repeatPassword" bundle="${val}"/>: </label>
                    <input type="password" name="repeatedPassword" value=""/>
                    <fmt:bundle basename="message">
                        <c:if test="${ incorrectRepeatedPassword eq true}"><fmt:message key="message.incorrectRepeatPassword" bundle="${message}"/></c:if>
                    </fmt:bundle>
                </div>
                <input type="hidden" name="command" value="update_user"/>
                <a href="#" class="button"
                   onclick="document.getElementById('form-1').submit(); return false;"><fmt:message key="button.update" bundle="${val}"/></a>
            </form>
        </div>
        <div class="form-wrapper">
            <div class="field">
                <label><fmt:message key="label.balance" bundle="${val}"/> : ${amount}</label>
            </div>
            <form action="/controller" id="form-2" method="post">
                <div class="field">
                    <label><fmt:message key="label.deposit" bundle="${val}"/></label>
                    <input type="text" name="amount" value="" placeholder="<fmt:message key="label.amount" bundle="${val}"/>"/>
                </div>
                <input type="hidden" name="currentAmount" value="${amount}">
                <input type="hidden" name="command" value="deposit_account"/>
                <fmt:bundle basename="message">
                <label class="invalid-value-label-clients"><c:if test="${errorBalanceMessage eq true}">
                    <fmt:message key="message.incorrectBalance" bundle="${message}"/>
                </c:if></label>
                </fmt:bundle>
                <a href="#" class="button"
                   onclick="document.getElementById('form-2').submit(); return false;"><fmt:message key="button.deposit" bundle="${val}"/></a>
            </form>
        </div>
    </div>
    </fmt:bundle>
    <div class="table-wrapper">
        <div class="wrapper">
            <c:if test="${ fn:length(subscriptions)> 0}">
                <fmt:bundle basename="table">
                <table border="2">
                    <caption><b><fmt:message key="caption.subscriptions" bundle="${value}"/></b></caption>
                    <tr>
                        <th><fmt:message key="th.id" bundle="${value}"/></th>
                        <th><fmt:message key="th.name" bundle="${value}"/></th>
                        <th><fmt:message key="th.start" bundle="${value}"/></th>
                        <th><fmt:message key="th.end" bundle="${value}"/></th>
                    </tr>
                    <c:forEach var="elem" items="${subscriptions}" varStatus="i">
                        <tr>
                            <td>${elem.id}</td>
                            <td>${elem.name}</td>
                            <td>${elem.startDay}</td>
                            <td>${elem.endDay}</td>
                        </tr>
                    </c:forEach>
                </table>
            </fmt:bundle>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
