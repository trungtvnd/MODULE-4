<%--
  Created by IntelliJ IDEA.
  User: LTC
  Date: 2/17/2022
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <p>RATE:</p>
    <label>
        <input name="rate" value="${rate}" placeholder="RATE">
    </label> <br>
    <p>USD:</p>
    <label>
        <input name="usd" value="${usd}" type="number" placeholder="USD">
    </label><br>
    <button type="submit">submit</button>
    <p>RESULT: ${result}</p>
</form>
</body>
</html>
