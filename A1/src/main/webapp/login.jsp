<%@ page import="com.example.a1.User" %><%--
  Created by IntelliJ IDEA.
  User: domin
  Date: 2021-10-18
  Time: 10:13 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Validation</title>
</head>
<body>


    <% String passcode = request.getParameter("passcode");

        if(passcode.equals("win")){
            response.sendRedirect(request.getContextPath() + "/PollManager.jsp");
        }
        else{
            out.println("Invalid passcode, return to previous page");
            //response.sendRedirect(request.getContextPath() + "/ErrorServlet");
        }
    %>


</body>
</html>
