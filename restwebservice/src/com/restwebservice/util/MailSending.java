package com.restwebservice.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
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

public class MailSending {
	static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    final String username = "aleksandrkitaygora@gmail.com";
	final String password = "lovelove855663";
    
    
    public void sendPassword(String emailAdress, String restoringPassword) {
        String mailBody = "Test email InspireMe Password restore. "+" Your new password is:  "+restoringPassword + "<br><br> Regards, <br>InspireMe Admin";
        try {
            generateAndSendEmail(emailAdress, mailBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendCongratulations(String emailAdress) {
        String mailBody = "InspireMe congratulate you with successful registration. "+ "<br><br> Sincerely, <br>InspireMe Admin";
        try {
            generateAndSendEmail(emailAdress, mailBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendMonthDelivery(String [] emailAdress) throws MessagingException {
        String mailBody = "InspireMe present you our month updates "+ "<br><br> Sincerely, <br>InspireMe Admin";
        //try {
        
            generateAndSendEmailWithAttachments(emailAdress, mailBody);
        /*} catch (MessagingException e) {
            e.printStackTrace();
        }*/
    }
    //отправка обновления всем подписчикам
    private void generateAndSendEmailWithAttachments(String[] emailAdress,
			String mailBody) throws MessagingException {
    	String attachment =  "D:\\Загрузки\\elAAN8DCOIc.jpg";
    	System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.auth", "true"); // Enable Authentication
        mailServerProperties.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.port", "587"); // TLS Port465.587.25

        System.out.println("Mail Server Properties have been setup successfully..");
        
      //Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        InternetAddress[] addressTo = new InternetAddress[emailAdress.length];
        for (int i = 0; i < emailAdress.length; i++) {
            addressTo[i] = new InternetAddress(emailAdress[i]);
        }
        generateMailMessage.setRecipients(Message.RecipientType.TO, addressTo);
        generateMailMessage.setSubject("InspireMe");
        BodyPart messageBodyPart = new MimeBodyPart();			
        messageBodyPart.setText("Find attachments");
        
        Multipart multipart = new MimeMultipart();	        
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(attachment);
        multipart.addBodyPart(attachmentBodyPart);
        generateMailMessage.setContent(multipart);
        try {
            generateMailMessage.setFrom(new InternetAddress("aleksandrkitaygora@gmail.com", "InspireME.com(localhost:8080)  - Admin"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }        
        System.out.println("Mail Session has been created successfully..");
 
//Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");
        // Enter your correct gmail UserID and Password
        transport.connect("smtp.gmail.com", "aleksandrkitaygora@gmail.com", "lovelove855663");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();   
    }
	private static void generateAndSendEmail(String emailAdress, String mBody) throws MessagingException {
        //String mailBody = mBody;
    //Step1
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587"); // TLS Port
            mailServerProperties.put("mail.smtp.auth", "true"); // Enable Authentication
            mailServerProperties.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS
            mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            System.out.println("Mail Server Properties have been setup successfully..");
     
    //Step2
            System.out.println("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            //generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("ostapulja@gmail.com"));       //кому
            generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(emailAdress));
            generateMailMessage.setSubject("InspireMe");
            String emailBody = mBody;
            try {
                generateMailMessage.setFrom(new InternetAddress("aleksandrkitaygora@gmail.com", "InspireME.com(localhost:8080)  - Admin"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");
     
    //Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");
            // Enter your correct gmail UserID and Password
            transport.connect("smtp.gmail.com", "aleksandrkitaygora@gmail.com", "lovelove855663");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        }
}
