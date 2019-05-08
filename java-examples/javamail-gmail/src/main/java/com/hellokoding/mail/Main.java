package com.hellokoding.mail;

public class Main {
    private static final String SMTP_SERVER_HOST = "smtp.gmail.com";
    private static final String SMTP_SERVER_PORT = "587";
    private static final String SUBJECT = "Sending mail with Gmail SMTP and Java Mail";
    private static final String BODY = "Hi,<br><br>This is a programmatic email.";

    public static void main(String[] args) {
        final String FROM_USER_EMAIL = args[0];
        final String FROM_USER_FULLNAME = args[1];
        final String FROM_USER_ACCESSTOKEN = args[2];
        final String TO_USER_EMAIL = args[3];

        new SendingMailThroughGmailSMTPServer().sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, FROM_USER_EMAIL, FROM_USER_ACCESSTOKEN, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);
    }
}
