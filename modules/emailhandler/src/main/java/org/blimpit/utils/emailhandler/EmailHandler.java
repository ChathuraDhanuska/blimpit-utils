package org.blimpit.utils.emailhandler;

import javax.mail.MessagingException;

/**
 * A interface which describes email handling APIs
 */
public interface EmailHandler {

    /**
     * Facilitates the email sending 
     * @param to email of the recipient
     * @param subject Subject of the email
     * @param body body of the email
     * @return send or not
     * @throws javax.mail.MessagingException
     */
    boolean sendEmail(String to, String subject, String body) throws MessagingException;
    
    /**
     * Send an email with attachment
     *
     * @param to          email of the recipient
     * @param subject     Subject of the email
     * @param body        of the email
     * @param attachement attachment path
     * @return send or not
     * @throws javax.mail.MessagingException
     */
    boolean sendEmailWithAttachment(String to, String subject, String body, String attachement) throws MessagingException;


}
