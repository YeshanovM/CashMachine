<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <title>Home</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
    <form method="get" action="/logout">
        <input type="submit" value="Logout">
    </form>
    <c:set var="access" value="access" />
    <c:if test="${sessionScope[access] == 1 || sessionScope[access] == 3}">
        <form method="get" action="/check">
            <input type="submit" value="Open check">
        </form>
    </c:if>
    <c:if test="${sessionScope[access] == 2 || sessionScope[access] == 3}">
        <form action="/cancelCheck" method="post">
            <input type="text" name="checkId" placeholder="Check id">
            <input type="submit" value="Cancel check">
        </form>
        ${checkCancelResult}
    </c:if>
    <c:if test="${sessionScope[access] == 4}">
        <form action="/productsMenu" method="post">
            <input type="submit" value="Open products menu">
        </form>
    </c:if>
</body>
</html>
