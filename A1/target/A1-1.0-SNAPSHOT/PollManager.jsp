<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.example.a1.Poll" %>
<%--
  Created by IntelliJ IDEA.
  User: domin
  Date: 2021-10-17
  Time: 6:22 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Poll Manager</title>
</head>
<body>
    <h1><%= "Hello Admin" %></h1>

    <div id="PollManagerOptions">
        <h2>1. Create Poll</h2>
        <div class="createPollOption">
            <form action="Poll-Servlet" method="get">
                How many choices will the poll have?<input type="number" step="1" name="numChoices">
                Name of Poll:<input type="text" name="name">
                Question:<input type="text" name="question">
                <input type="submit" name="CreatePoll" value="CreatePoll"/>
            </form>
        </div>
        <br>
        <h2>2. Update Poll</h2>
        <div class="createPollOption">
            <form action="Poll-Servlet" method="get">
                New name of poll:<input type="text" name="newName">
                New question<input type="text" name="newQuestion">
                <input type="submit" name="UpdatePoll" value="UpdatePoll"/>
            </form>
        </div>
        <c:if test="${User.poll != null}">
            <form action="Poll-Servlet" method="get">
                <c:forEach items="${User.poll.choices}" var="entry">
                    ${entry.key.text} <input type="text" name="${entry.key.text}"> <br>
                </c:forEach>
                <input type="submit" name="UpdatePollChoices" value="UpdatePollChoices"/>
            </form>
        </c:if>
        <h2>3. Clear Poll</h2>
        <div class="clearPollOption">
            <form action="Poll-Servlet" method="get">
                <input type="submit" name="ClearPoll" value="ClearPoll"/>
            </form>
        </div>
        <h2>4. Close Poll</h2>
        <div class="closePollOption">
            <form action="Poll-Servlet" method="get">
                <input type="submit" name="ClosePoll" value="ClosePoll"/>
            </form>
        </div>
        <h2>5. Run Poll</h2>
        <div class="runPollOption">
            <form action="Poll-Servlet" method="get">
                <input type="submit" name="RunPoll" value="RunPoll"/>
            </form>
        </div>
        <h2>6. Release Poll</h2>
        <div class="releasePollOption">
            <form action="Poll-Servlet" method="get">
                <input type="submit" name="ReleasePoll" value="ReleasePoll"/>
            </form>
        </div>
        <h2>7. Unrelease Poll</h2>
        <div class="unreleasePollOption">
            <form action="Poll-Servlet" method="get">
                <input type="submit" name="UnreleasePoll" value="UnreleasePoll"/>
            </form>
        </div>
        <h2>8. Vote</h2>
        <div class="voteOption">
            <c:if test="${User.poll != null}">
                <c:out value="${User.poll.toString()}"/>
                <form id="Vote" action="Poll-Servlet" method="post">
                    <c:forEach items="${User.poll.choices}" var="entry">
                        ${entry.key.text} <input type="radio" name="${entry.key.text}"> <br>
                    </c:forEach>
                    <input type="submit" name="Vote" value="Vote"/>
                </form>
            </c:if>
        </div>
        <h2>9. Get Poll Results</h2>
        <div class="getPollResultsOption">
            <form action="Poll-Servlet" method="post">
                <input type="submit" name="GetPoll" value="GetPoll"/>
            </form>
        </div>
        <h2>10. Download Poll Details</h2>
        <div class="downloadPollResultsOption">
            <form action="Poll-Servlet" method="post">
                <input type="submit" name="DownloadPoll" value="DownloadPoll"/>
            </form>
        </div>
    </div>


</body>
</html>
