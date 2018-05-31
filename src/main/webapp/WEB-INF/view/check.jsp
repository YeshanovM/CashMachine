<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
    Check id: ${check.getId()}<br>
    Cashier: ${check.getCashierId()}<br>
    Time: ${check.getTimestamp()}
    <c:set var="sum" value="0"/>
    <table>
        <c:forEach items="${productsInCheck}" var="product">
            <tr>
                <td>
                    ${product.getName()} ${product.getQuantity()} x ${product.getPrice()} = ${product.getQuantity() * product.getPrice()}
                    <c:set var="sum" value="${sum + product.getQuantity() * product.getPrice()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    Total sum: ${sum}
    <form action="/" method="get">
        <input type="submit" value="Home">
    </form>
</body>
</html>