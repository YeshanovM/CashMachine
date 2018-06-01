<%--
  Created by IntelliJ IDEA.
  User: Royk
  Date: 01.06.2018
  Time: 6:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products menu</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
    <table>
        <c:forEach items="${allProducts}" var="item">
            <tr>
                <form action="/productsMenu" method="post">
                    <input type="hidden" name="editProduct">
                    <input type="hidden" name="code" value="${item.getCode()}">
                    <td>${item.getCode()}</td>
                    <td>${item.getName()}</td>
                    <td><input type="text" name="quantity" value="${item.getQuantity()}"></td>
                    <td><input type="submit" value="Save"></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <form action="/productsMenu" method="post">
        <table>
            <input type="hidden" name="addProduct">
            <tr>
                <td><input type="text" name="code" value="${code}" placeholder="Product code"></td>
            </tr>
            <tr>
                <td><input type="text" name="name" value="${name}" placeholder="Product name"></td>
            </tr>
            <tr>
                <td><input type="text" name="quantity" value="${quantity}" placeholder="Quantity"></td>
            </tr>
            <tr>
                <td><input type="text" name="price" value="${price}" placeholder="Price"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="isWeighty" checked>is weighty</td>
            </tr>
            <tr>
                <td><input type="submit" value="Add product"></td>
            </tr>
        </table>
    </form>
    ${error}
</body>
</html>
