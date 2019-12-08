<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/24/2019
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>User account</title>
    <link href="../css/account-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="main-wrapper">
    <div class="two-form-wrapper">
        <div class="form-wrapper">
            <form action="/controller" id="form-1">
                <label class="caption">Change</label>
                <div class="field">
                    <label>First name:</label>
                    <input type="text" name="firstName" value="${currentUser.firstName}"/>
                </div>
                <div class="field">
                    <label>Last name:</label>
                    <input type="text" name="lastName" value="${currentUser.lastName}"/>
                </div>
                <div class="field">
                    <label>Email: </label>
                    <input type="email" name="email" value="${currentUser.email}"/>
                </div>
                <div class="field">
                    <label>Password: </label>
                    <input type="password" name="password" value=""/>
                </div>
                <div class="field">
                    <label>Repeat password: </label>
                    <input type="password" name="repeatedPassword" value=""/>
                </div>
                <input type="hidden" name="command" value="update_user"/>
                <a href="#" class="button"
                   onclick="document.getElementById('form-1').submit(); return false;">Войти</a>
            </form>
        </div>
        <div class="form-wrapper">
            <div class="field">
                <label>Balance : ${amount}</label>
            </div>
            <form action="/controller" id="form-2" method="post">
                <div class="field">
                    <label>Пополнить счёт</label>
                    <input type="text" name="amount" value="" placeholder="Сумма"/>
                </div>
                <input type="hidden" name="currentAmount" value="${amount}">
                <input type="hidden" name="command" value="deposit_account"/>
                <label class="invalid-value-label-clients">${errorBalanceMessage}</label>
                <a href="#" class="button"
                   onclick="document.getElementById('form-2').submit(); return false;">Войти</a>
            </form>
        </div>
    </div>
    <div class="table-wrapper">
        <div class="wrapper">
            <c:if test="${ fn:length(subscriptions)> 0}">
                <table border="2">
                    <caption><b>Subscription</b></caption>
                    <tr>
                        <th>ID</th>
                        <th>Subscription name</th>
                        <th>Start day</th>
                        <th>End day</th>
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
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
