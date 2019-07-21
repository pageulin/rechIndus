package idd.rechercheIndus.application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.logging.Logger;

import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import idd.rechercheIndus.application.entity.Mail;
import idd.rechercheIndus.application.utils.MimeType;

@Service
public class SendGridMailSenderService {
	
	@Value("${mail.key.sendgrid}")
	private String sendGridKey;
	private static final Logger logger = Logger.getLogger(SendGridMailSenderService.class.getName());
	
	public void sendMail(Mail mail) {

		Email from = new Email(mail.getSender());
	    String subject = mail.getSubject();
	    Email to = new Email(mail.getRecipient());
	    Content content = new Content("text/html", mail.getMessage());
	    com.sendgrid.helpers.mail.Mail mailToSend = new com.sendgrid.helpers.mail.Mail(from, subject, to, content);

	    if(mail.getAttachments() != null) {
	    	mail.getAttachments().forEach(resource -> {
    		  try {
				String fileName = resource.getFile().getName();
				String[] splitName = fileName.split("\\.");
				MimeType mimeType = null;
				String contentId = null;
				if(splitName.length > 0)
				{
					contentId = splitName[0];
					mimeType = MimeType.of(splitName[1]);
				}
				if(mimeType != null && contentId != null) {
					Attachments attachments = new Attachments();
					StringBuilder sb = new StringBuilder();
					String line = null;
					try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), Charsets.UTF_8))) {	
						while ((line = bufferedReader.readLine()) != null) {
							sb.append(line);
						}
					}
					String base64Content = Base64.getEncoder().encodeToString(sb.toString().getBytes());
					attachments.setContent(base64Content); 
					attachments.setType(mimeType.getMimeType());
				    attachments.setFilename(fileName);
				    attachments.setDisposition("inline");
				    attachments.setContentId(contentId);
				    mailToSend.addAttachments(attachments);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	});
	    }
	    SendGrid sg = new SendGrid(sendGridKey);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mailToSend.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	    	logger.warning(ex.getMessage());
	    }		
	}
}
