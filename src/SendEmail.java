
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pradi
 */
public class SendEmail {
    public String to,message,subject;
    SendEmail(String too,String msg,String sbj)
    {
        to=too;
        message=msg;
        subject=sbj;
        send();
    }
    void send()
    {
    try
        {
            String host = "smtp.gmail.com";
            String user ="***REMOVED***";
            String pass="***REMOVED***";
            //String subject="Reseting Code";
           // String message ="Your reset code is "+randomCode;
            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.user","username"); 
            props.put("mail.smtp.host", "smtp.gmail.com"); 
            props.put("mail.smtp.port", "25"); 
            props.put("mail.debug", "true"); 
            props.put("mail.smtp.auth", "true"); 
            props.put("mail.smtp.starttls.enable","true"); 
            props.put("mail.smtp.EnableSSL.enable","true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
            props.setProperty("mail.smtp.socketFactory.fallback", "false");   
            props.setProperty("mail.smtp.port", "465");   
               props.setProperty("mail.smtp.socketFactory.port", "465"); 
            props.put("mail.smtp.starttls.required", "true");
            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress [] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch(Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);
  
        }
    }
}
