<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Activate account with Spring Boot, MongoDB, NGINX, Docker Compose</title>
</head>
<body>
    <h2>Verify your email</h2>

    <@spring.bind "verificationForm"/>
    <#if verificationForm?? && noErrors??>
    Sent a confirmation link to your inbox ${verificationForm.email}<br>
    <#else>
    <form action="/email-verification" method="post">
        Email:<br>
        <@spring.formInput "verificationForm.email"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    </#if>
</body>
</html>