<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Form example with Java, Spring Boot, FreeMarker</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h2>Handling Form Submission example with Java, Spring Boot, FreeMarker</h2>
        <#if user?? >
        Your submitted data<br>
        First name: ${user.firstName}<br>
        Last name: ${user.lastName}<br>
        <#else>
        <form action="/form" method="post">
            First name:<br>
            <input type="text" name="firstName">
            <br><br>
            Last name:<br>
            <input type="text" name="lastName">
            <br><br>
            <input type="submit" value="Submit">
        </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>