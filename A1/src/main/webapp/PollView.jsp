<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: domin
  Date: 2021-10-17
  Time: 6:41 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Graphical View</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Choice');
            data.addColumn('number', 'Votes');

            <c:forEach items="${User.poll.choices}" var="entry">
                data.addRow(['${entry.key.text}',${entry.value}]);
            </c:forEach>


            var options = {
                title: '${User.poll.name}',
                hAxis: {
                    title: 'Poll Options'
                },
                vAxis: {
                    title: 'Number of Votes'
                }
            };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
            chart.draw(data, options);

        }
    </script>
</head>
<body>
<div id="chart_div"></div>
</body>
<form id="DownloadResults" action="Poll-Servlet" method="post">
    <input type="button" value="DownloadPoll" name="DownloadPoll"/>
</form>
<form id="Vote" action="Poll-Servlet" method="post">
    <input type="button" value="Vote" name="Vote"/>
</form>
</html>

