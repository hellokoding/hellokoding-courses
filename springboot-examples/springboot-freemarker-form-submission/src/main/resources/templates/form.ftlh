<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot Form Submission</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Spring Boot Form Submission</h1>
        <#if user?? >
            Your submitted data<br>
            Name: ${user.name}<br>
            Message: ${user.message}<br>
        <#else>
            <form action="/form" method="post">
                <div>Name:</div>
                <input type="text" name="name"/>
                <br/><br/>
                <div>Message:</div>
                <textarea rows="2" name="message"></textarea>
                <br/><br/>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>