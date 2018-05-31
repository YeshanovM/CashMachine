<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Opened check</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
    <c:set var="products" value="productsInCheck"/>
    <c:set var="uid" value="uid"/>
    <table>
        <c:forEach items="${sessionScope[products]}" var="product">
            <tr>
                <td>
                    ${product.getName()} ${product.getQuantity()} x ${product.getPrice()} = ${product.getQuantity() * product.getPrice()}<br>
                </td>k
            </tr>
        </c:forEach>
    </table>
    <c:if test="${sessionScope[products].size() > 0}">
        <form action="/closeCheck" method="post">
            <input type="submit" value="Close check">
        </form>
    </c:if>
    <form action="/check" method="post">
        <table>
            <tr>
                <td colspan="2">
                    <input type="text" placeholder="Product code" value="${code}" name = "code">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <select name="name">
                        <c:forEach items="${productsNames}" var="item">
                            <option><c:out value="${item}" /></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="text" placeholder="Quantity" value="${quantity}" name="quantity" required>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Search by:
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="searchBy" value="code" checked>Code
                </td>
                <td>
                    <input type="radio" name="searchBy" value="name">Name
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Add product">
                </td>
            </tr>
        </table>
    </form>
    ${error}
</body>
</html>
