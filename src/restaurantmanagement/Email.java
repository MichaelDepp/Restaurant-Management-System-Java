/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmanagement;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Michael Depp
 */
public class Email {

    private final String recipient;
    private final String sender = "youremail@gmail.com"; //your company email
    private final String pass = "yourpassword"; //your company password
    String host = "localhost"; //defining the localhost 
    String sales;

    Email(String recipient, String sales) {
        this.recipient = recipient;
        this.sales = sales;
    }

    public void sendmail() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", true); //checking whether the smtp is true 
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com"); //checking the hosting email 
        props.put("mail.smtp.port", "587"); //setting the port

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { //creating a new session and password is set protected
                return new PasswordAuthentication(sender, pass);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender)); //our email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject("Sales Summary"); //setting subject
            message.setText("Try");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            String file = "Revenue-" + sales + ".txt"; //the file name is being defined
            String fileName = "salesummary";
            messageBodyPart = new MimeBodyPart(); //the message body
            DataSource source = new FileDataSource(file); //attaching file which is the txt file
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");
            Transport.send(message); //sending the message
            System.out.println("Email Sent!");

        } catch (MessagingException e) {
            e.printStackTrace(); //exception incase the message didnt delivered
        }
        //exception incase the message didnt delivered

    }
}
