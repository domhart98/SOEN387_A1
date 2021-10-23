<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: domin
  Date: 2021-10-22
  Time: 8:43 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Poll Results</title>
</head>
<body>
  <c:forEach items="${User.poll.choices}" var="entry">
    Key = ${entry.key.text}, value = ${entry.value}<br>
  </c:forEach>
  <button type="button" name="home" action="">home</button>
</body>
</html>
