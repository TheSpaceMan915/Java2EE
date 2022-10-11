<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%
    //getting the list from attributes
   // List<ValutePeriod> list = new ArrayList<>();
   // list = (ArrayList<ValutePeriod>) request.getAttribute("valutes");
%>
<head>
    <title>Title</title>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

        // Load the Visualization API and the corechart package.
        google.charts.load('current', {'packages':['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart()
        {
            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Date');
            data.addColumn('string', 'Value');

            <c:forEach items="${requestScope.values()}" var="item">
            data.addRow([item.m_date,item.m_value]);
            </c:forEach>

            // Set chart options
            var options = {'title':'How Much Pizza I Ate Last Night',
                'width':400,
                'height':300};

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>

<body>
<!--Div that will hold the pie chart-->
<div id="chart_div"></div>
</body>
</html>
