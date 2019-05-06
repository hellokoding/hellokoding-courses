<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authentication Service</title>
</head>
<body>
<form method="POST" action="/login?redirect=${RequestParameters.redirect!}">
    <h2>Log in</h2>
    <input name="username" type="text" placeholder="Username"
           autofocus="true"/>
    <input name="password" type="password" placeholder="Password"/>
    <div>(try username=hellokoding and password=hellokoding)</div>
    <div style="color: red">${error!}</div>
    <br/>
    <button type="submit">Log In</button>
</form>
</body>
</html>