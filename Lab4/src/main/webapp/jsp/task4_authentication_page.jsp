<html lang="en">
<head>
    <style>
        h1 {text-align: center}
    </style>
    <meta charset="UTF-8">
    <title>Authentication</title>
</head>
<body>

<h1>Authentication page</h1>

<form action="${pageContext.request.contextPath}/ServletAuthenticator" method = "POST">
    <label for="log">Login</label><br>
    <input type="text" id="log" name="field_login">
    <br><br>

    <label for="pass">Password</label><br>
    <input type="password" id="pass" name="field_password">
    <br><br>

    <input type="submit" value="Submit">
</form>

</body>
</html>