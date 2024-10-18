package restservice;

import java.util.Properties;

import javax.activation.DataHandler;
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
import javax.mail.util.ByteArrayDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Email;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailController {

    private final String FROM = "sensorlogicsystem.help@gmail.com";
    private final String PASSWORD_FROM = "ProvaFinale2020";
	
	@PostMapping("/sendEmail")
	public boolean sendEmail(@RequestBody Email email) {

		boolean result = true;

	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(FROM, PASSWORD_FROM);  
	   }  
	   });  

	      try {	    	  		    	 
		         Multipart multipart = new MimeMultipart();
		         BodyPart messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setText(email.getText());
		         multipart.addBodyPart(messageBodyPart);

		         if(email.getFile() != null) {
			         messageBodyPart = new MimeBodyPart();
			    	 byte[] byteFile = Base64.decodeBase64(email.getFile());
			         ByteArrayDataSource bds = new ByteArrayDataSource(byteFile, "application/octet-stream"); 
			         messageBodyPart.setDataHandler(new DataHandler(bds));
			         if(bds.getName() != null && !bds.getName().isEmpty()) {
			        	 messageBodyPart.setFileName(bds.getName());
			         } else {
			        	 messageBodyPart.setFileName("documento.pdf");
			         }
			         multipart.addBodyPart(messageBodyPart);
		         }

	
		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(FROM));
		         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
		         message.setSubject(email.getSubject());
		         message.setContent(multipart);
	
		         Transport.send(message);
	      } catch (MessagingException mex) {
	    	 result = false;
	         mex.printStackTrace();
	      }
		
		return result;
	}
}
