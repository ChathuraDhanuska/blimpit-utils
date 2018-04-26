/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blimpit.utils.emailhandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.MessagingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Chathura
 */
public class EmailServiceTest {
    
    private String GmailAddress;
    private String GmailPassword;
    
    private String YahooAddress;
    private String YahooPassword;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    
    public EmailServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        /*
        * Read email addressess and passwords from config file
        */
        File configFile = new File("config.properties");
        
        try (FileReader reader = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(reader);
            
            GmailAddress = props.getProperty("GmailAddress");
            GmailPassword = props.getProperty("GmailPassword");
            YahooAddress = props.getProperty("YahooAddress");
            YahooPassword = props.getProperty("YahooPassword");
            
        } catch (FileNotFoundException ex) {
            System.err.println("Config file not found: " + ex);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sendEmail method, of class EmailService.
     * @throws java.lang.Exception
     */
    @Test
    public void testSendEmail() throws Exception {
        System.out.println("sendEmail with all correct parameters");
        String to = "blimpit.test@yahoo.com";
        String subject = "Test email";
        String body = "Hi Test,\n\nThis is a test email\n\nThanks,\nTest.";
        EmailService instance = new EmailService(GmailAddress, GmailPassword);
        boolean expResult = true;
        boolean result = instance.sendEmail(to, subject, body);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sendEmail method, of class EmailService.
     * - Test case give a non existing email address as recipient
     * @throws Exception
     */
    @Test(expected = MessagingException.class)
    public void testSendEmailWithWrongAddress() throws Exception {
        System.out.println("sendEmail with invalid email address");
        String to = "blimpit.test.com";
        String subject = "Test email";
        String body = "Hi Test,\n\nThis is a test email\n\nThanks,\nTest.";
        EmailService instance = new EmailService(GmailAddress, GmailPassword);
        instance.sendEmail(to, subject, body);
    }

    /**
     * Test of sendEmailWithAttachment method, of class EmailService.
     */
  /*  @Test
    public void testSendEmailWithAttachment() throws Exception {
        System.out.println("sendEmailWithAttachment");
        String to = "";
        String subject = "";
        String body = "";
        String attachement = "";
        EmailService instance = null;
        boolean expResult = false;
        boolean result = instance.sendEmailWithAttachment(to, subject, body, attachement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
