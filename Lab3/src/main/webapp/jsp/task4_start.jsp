<html>
<head>
    <style>
        h1 {text-align: center; color: darkviolet}
    </style>
    <title>Tic-Tac-Toe</title>
</head>
<body>
<h1>Play the classic Noughts and Crosses game</h1>
<br><br>

<%--if the action is not working, try using this one--%>
<%--../ServletController--%>
<form action="ServletController" method = "POST">
    <label for="row">Row</label><br>
    <input type="text" id="row" name="field_row"><br>

    <label for="column">Column</label><br>
    <input type="text" id="column" name="field_column"><br>
    <input type="submit" value="Enter">
</form>
<br><br>

</body>
</html>
