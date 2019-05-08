package com.hellokoding.mail;

public class Main {
    private static final String SMTP_SERVER_HOST = "email-smtp.us-west-2.amazonaws.com";
    private static final String SMTP_SERVER_PORT = "587";
    private static final String SUBJECT = "Sending mail with SES SMTP and Java Mail";
    private static final String BODY = "Hi,<br><br>This is a programmatic email.";

    public static void main(String[] args) {
        final String SMTP_USER_NAME = args[0];
        final String SMTP_USER_PASSWORD = args[1];
        final String FROM_USER_EMAIL = args[2];
        final String FROM_USER_FULLNAME = args[3];
        final String TO_USER_EMAIL = args[4];

        SendingMailThroughAWSSESSMTPServer sendingMailThroughAWSSESSMTPServer = new SendingMailThroughAWSSESSMTPServer();
        sendingMailThroughAWSSESSMTPServer.sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, SMTP_USER_NAME, SMTP_USER_PASSWORD, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);
    }
}
