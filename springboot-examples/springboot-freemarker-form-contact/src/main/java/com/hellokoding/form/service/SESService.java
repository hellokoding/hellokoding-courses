package com.hellokoding.form.service;

import com.hellokoding.form.model.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SESService implements SendingMailService {
    private final MailProperties mailProperties;

    @Autowired
    SESService(MailProperties mailProperties){
        this.mailProperties = mailProperties;
    }

    public boolean sendMail(String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", mailProperties.getSmtp().getPort());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getFrom(), mailProperties.getFromName()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailProperties.getTo()));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(mailProperties.getSmtp().getHost(), mailProperties.getSmtp().getUsername(), mailProperties.getSmtp().getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }
}
