<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report</title>
</head>
<body>
<table>
    <tr>
        <td colspan="2">Report</td>
    </tr>
    <tr>
        <td>From:</td>
        <td>${report.getStartTime()}</td>
    </tr>
    <tr>
        <td>To:</td>
        <td>${report.getEndTime()}</td>
    </tr>
    <tr>
        <td>Total incomes:</td>
        <td>${report.getIncomes()}</td>
    </tr>
    <tr>
        <td>Total expenses:</td>
        <td>${report.getExpenses()}</td>
    </tr>
</table>
</body>
</html>
