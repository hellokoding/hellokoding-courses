<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Contact Form Example</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h2>Contact Form Example</h2>

        <@spring.bind "user"/>
        <#if user?? && noErrors??>
            Your data has been sent<br>
            Name: ${user.name}<br>
            Email: ${user.email}<br>
            Message: ${user.message}<br>
        <#else>
            <form action="/form" method="post">
                Name:<br>
                <@spring.formInput "user.name"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Email:<br>
                <@spring.formInput "user.email"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Message:<br>
                <@spring.formTextarea "user.message"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                <input type="submit" value="Submit">
            </form>
        </#if>

        <script src="/js/main.js"></script>
    </body>
</html>