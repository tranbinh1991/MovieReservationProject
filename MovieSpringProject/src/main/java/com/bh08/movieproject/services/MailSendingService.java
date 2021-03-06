/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.models.User;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Binh
 */
@Service
public class MailSendingService {
    
    @Value("${bhcinema.mail.attachmentpath}")
    private String attachmentPath;

    public void sendMail(User recipient, String attachmentFileName) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("X@X.com", "X");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("nezunkmintamoziban@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient.getEmail()));
        msg.setSubject("Nézünkmintamoziban jegyfoglaló visszaigazolás");
        msg.setContent("Kedves" + recipient.getEmail() + "<br/>"+ "<br/>"
                
                +"Köszönjük, hogy használtad szolgáltatásainkat!" + "<br/>"
                +"A csatolmányban találod az általad lefoglalt jegyek részleteit" + "<br/>" + "<br/>"
                +"Üdvözlettel:" + "<br/>" + "<br/>"
                +"A nézünkmintamoziban csapata"
                , "text/html");
        msg.setSentDate(new Date());
        
        StringBuffer emailMessage = createMessageBody(recipient);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Kedves "+emailMessage, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile(attachmentPath+attachmentFileName);
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);

    }

    private StringBuffer createMessageBody(User recipient) {
        StringBuffer emailMessage = new StringBuffer(recipient.getEmail());
        emailMessage.append("<br/>");
        emailMessage.append("<br/>");
        emailMessage.append("Köszönjük, hogy használtad szolgáltatásainkat!");
        emailMessage.append("<br/>");
        emailMessage.append("<br/>");
        emailMessage.append("A csatolmányban találod az általad lefoglalt jegyek részleteit");
        emailMessage.append("<br/>");
        emailMessage.append("<br/>");
        emailMessage.append("Üdvözlettel:");
        emailMessage.append("<br/>");
        emailMessage.append("<br/>");
        emailMessage.append("A nézünkmintamoziban csapata");
        return emailMessage;
    }
}
