package com.hellokoding.mail;


import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendingMailThroughGmailSMTPServer {
    void sendMail(String smtpServerHost, String smtpServerPort,  String smtpUserName, String smtpUserAccessToken, String fromUserEmail, String fromUserFullName, String toEmail, String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", smtpServerPort);
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromUserEmail, fromUserFullName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            SMTPTransport transport = new SMTPTransport(session, null);
            transport.connect(smtpServerHost, smtpUserName, null);
            transport.issueCommand("AUTH XOAUTH2 " + new String(BASE64EncoderStream.encode(String.format("user=%s\1auth=Bearer %s\1\1", smtpUserName, smtpUserAccessToken).getBytes())), 235);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
