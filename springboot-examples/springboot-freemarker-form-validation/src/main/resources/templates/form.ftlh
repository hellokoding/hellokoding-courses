<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Form Data Binding and Validation</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Form Data Validation and Binding</h1>
        <#if user?? && noErrors??>
            Your submitted data<br>
            First name: ${user.firstName}<br>
            Last name: ${user.lastName}<br>
            Email: ${user.email}<br>
        <#else>
            <form action="/form" method="post">
                First name:<br>
                <@spring.formInput "user.firstName"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Last name:<br>
                <@spring.formInput "user.lastName"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Email:<br>
                <@spring.formInput "user.email"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                <input type="submit" value="Submit">
            </form>
        </#if>

        <script src="/js/main.js"></script>
    </body>
</html>