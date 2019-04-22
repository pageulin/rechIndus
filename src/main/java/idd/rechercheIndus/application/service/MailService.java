package idd.rechercheIndus.application.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;

	public String sendMail(String message, String subject, String recipient) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		helper.setText(templateEngine.process("mail/template.html", new Context()), true);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.addInline("img01", new ClassPathResource("templates/mail/images/img-01.jpg"), "image/jpg");
		helper.setFrom("recherche-industrielle@yopmail.com");
		mailSender.send(mimeMessage);
		return message;
	}

}
