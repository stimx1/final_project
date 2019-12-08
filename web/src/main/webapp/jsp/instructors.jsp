<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxxx
  Date: 11/4/2019
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/table-style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="main.jsp"/>
<div class="table-wrapper">

    <table border="2">
        <caption><b>Instructors</b></caption>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Info</th>
            <th>Selected</th>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <td>Action</td>
            </c:if>
        </tr>
        <c:forEach var="elem" items="${instructors}">
            <tr>
                <td>${elem.id}</td>
                <td>${elem.lastName}</td>
                <td>${elem.info}</td>
                <td>${elem.firstName}</td>
                <td>
                    <c:choose>
                        <c:when test="${ selectedId eq elem.id }">
                            <input type="radio" name="instructorId" value="${elem.id}" checked/>
                        </c:when>
                        <c:otherwise>
                            <form action="/controller" method="post" id="form-1" >
                                <input type="radio" name="instructorId" value="${elem.id}" onchange="this.form.submit()"/>
                                <input type="hidden" name="command" value="change_instructor"/>
                                <input type="hidden" name="redirect" value="/controller?command=get_instructors"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:if test="${ currentUser.role eq 'ADMIN' }">
                    <a href="/controller?command=delete_instructor&instructor_id=${elem.id}&redirect=/controller?command=get_instructors" >delete</a>
                </c:if></td>
            </tr>
        </c:forEach>
        <tr>
            <c:if test="${ currentUser.role eq 'ADMIN' }">
                <td>?</td>
                <form id="form-2" method="post" action="/controller">
                    <input type="hidden" name="command" value="add_instructor"/>
                    <input type="hidden" name="redirect" value="/controller?command=get_instructors">
                    <td><input type="text" name="first_name" value=""/></td>
                    <td><input type="text" name="last_name" value=""/></td>
                    <td><input type="text" name="info" value=""/></td>
                    <td>&nbsp;</td>
                    <td><a href="#" class="button" onclick="document.getElementById('form-2').submit(); return false;">Add</a></td>
                </form>
            </c:if>
        </tr>
    </table>



</div>
</body>
</html>
