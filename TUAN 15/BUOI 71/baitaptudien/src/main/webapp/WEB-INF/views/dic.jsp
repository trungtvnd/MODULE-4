<%--
  Created by IntelliJ IDEA.
  User: LTC
  Date: 2/17/2022
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dictionary</h1>
<form method="post">
    <label>
        <input name="key" value="${key}" placeholder="word">
    </label><br>
    <p>Result: ${result}</p>
    <button type="submit" value="Submit">Submit</button>
</form>
</body>
</html>
