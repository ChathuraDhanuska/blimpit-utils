package org.blimpit.utils.emailhandler;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService implements EmailHandler {
    
    private Properties props;
    private String sender;
    private String password;
    
    public EmailService(String sender, String password) {
        this.sender = sender;
        this.password = password;
        // calling to work with default settings
        setDefaultProperties();
    }
    
    public EmailService(String sender, String password, Properties props) {
        this.sender = sender;
        this.password = password;
        this.props = props;
    }

    public void setProperties(Properties props) {
        this.props = props;
    }
    
    @Override
    public boolean sendEmail(String to, String subject, String body) throws MessagingException {
        Session session = Session.getDefaultInstance(props, 
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        return true;
    }
    
    @Override
    public boolean sendEmailWithAttachment(String to, String subject, String body, String attachement) throws MessagingException {
        Session session = Session.getDefaultInstance(props, 
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        
        // Create a multi part object to message
        Multipart multiPart = new MimeMultipart();
        
        // Set message text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        multiPart.addBodyPart(messageBodyPart);
        
        // Set message attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachement);
        messageBodyPart.setDataHandler(new DataHandler(source));
        File file = new File(attachement);
        messageBodyPart.setFileName(file.getName());
        multiPart.addBodyPart(messageBodyPart);
        
        // complete the message
        message.setContent(multiPart);

        Transport.send(message);

        return true;
    }
    
    private void setDefaultProperties() {
        switch(getMailDomain()) {
            case "gmail":
                setDefaultGmailProperties();
                break;
                
            case "yahoo":
                setDefaultYahooProperties();
                break;
                
            case "hotmail":
                setDefaultHotmailProperties();
                break;
                
            default:
                // impliment the error handling section for not setting up a email
        }
    }
    
    private String getMailDomain() {
        String[] sections = sender.split("@");
        
        if(sections.length != 2)
            return null;
        
        String[] parts = sections[1].split("\\.");
        
        if(parts.length != 2)
            return null;
        
        return parts[0];
    }
    
    /*
     * Set Default properties for Gmail
     * SMTP Gmail via SSL implimentation of email service
     */
    private void setDefaultGmailProperties() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }
    
    /*
     * Set Default properties for Yahoo
     * SMTP Yahoo via SSL implimentation of email service
     */

    private void setDefaultYahooProperties() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.debug", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
    }
    
    /*
     * Set Default properties for Hotmail
     * SMTP Hotmail via SSL implimentation of email service
     */

    private void setDefaultHotmailProperties() {
        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.live.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
    }
    
}