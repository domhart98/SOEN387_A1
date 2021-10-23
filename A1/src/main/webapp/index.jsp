<%@ page import="com.example.a1.User" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>SOEN A1 - POLL</title>
</head>
<body>
    <h1><%= "Hello Anon" %></h1>
    <br/>

    <jsp:useBean id="user" class="com.example.a1.User" scope="session">
    </jsp:useBean>

    <%session.setAttribute("User", user);%>


    <div id="poll">
        <h2>Poll:</h2>

            <c:if test="${User.poll == null}">
                A poll has not been created yet.
            </c:if>
            <c:if test="${User.poll != null}">
                <c:out value="${User.poll.toString()}"/>
                <br>
                <form id="Vote" action="Poll-Servlet" method="post">
                    <c:forEach items="${User.poll.choices}" var="entry">
                        ${entry.key.text} <input type="radio" name="UserVote"> <br>
                    </c:forEach>
                    <input type="submit" name="Vote" value="Vote"/>
                </form>
                <br>
                <form id="DownloadResults" action="Poll-Servlet" method="post">
                    <input type="button" value="DownloadPoll" name="DownloadPoll"/>
                </form>
                <br>
                <form id="ViewResults" action="PollView.jsp" method="post">
                    <input type="button" value="GetPoll" name="GetPoll"/>
                </form>
            </c:if>


    </div>
    <br>
    <form id="enterPasscode" action="login.jsp" method="post">

        Enter passcode to access business layer: <input type="text" name="passcode"/>
        <input type="submit" value="Submit"/>

    </form>

</body>
</html>