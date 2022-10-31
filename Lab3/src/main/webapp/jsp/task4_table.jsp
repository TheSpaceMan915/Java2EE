<%@ page import="classes.task4.GameHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% final String[][] arr = (String[][]) application.getAttribute("array"); %>
<html>
<head>
    <style>
        table,td,th {border: 1px solid black;margin-left:auto;margin-right:auto}
        table { width: 40%; height: 40% }
        th {height: 40px; width: 40px}
        td {height: 60px; width: 60px; text-align: center}
    </style>
    <title>Table page</title>
</head>
<body>

<table>
    <tr>
        <th>0</th>
        <th>1</th>
        <th>2</th>
    </tr>
    <%
        for (int i = 0; i < 3; i++)
        {
            out.println("<tr>");
            for (int j = 0; j < 3; j++)
            {
                out.println("<td>" + arr[i][j] + "</td>");
            }
            out.println("</tr>");
        }
    %>
</table>
<br><br><br>

<%
    if (GameHelper.getUserFlag())
    {
        out.println("<p1 style=color:orangered;font-size:300%;>");
        out.println("Congratulations! You've won!");
        out.println("</p1>");
    }
    else if (GameHelper.getServerFlag())
    {
        out.println("<p1 style=color:coral;font-size:300%;>");
        out.println("The server has won!");
        out.println("</p1>");
    }
    else
    { out.println("<a href = \"http://localhost:8080/Lab3-1.0-SNAPSHOT/\">Go back</a>"); }
%>
</body>
</html>
