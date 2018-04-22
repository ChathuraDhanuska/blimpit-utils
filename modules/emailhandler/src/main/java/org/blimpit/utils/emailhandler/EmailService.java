package org.blimpit.utils.emailhandler;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService implements EmailHandler {
    
    private Properties props;
    private String password;
    private String sender;

    public EmailService() {
        setDefaultProperties();
    }
    
    public EmailService(String sender, String password) {
        this.sender = sender;
        this.password = password;
        
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

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /*
     * Set Default properties for Gmail
     * SMTP Gmail via SSL implimentation of email service
     */
    private void setDefaultProperties() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }
    
    
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        Session session = Session.getDefaultInstance(props, 
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, password);
                    }
                });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            
            Transport.send(message);
            
            return true;
        } catch (MessagingException e) {
            System.err.println("Exception: " + e);
            return false;
        }
    }

	@Override
	public boolean sendEmailWithAttachment(String to, String subject, String attachement) {
		return false;
	}
    
}