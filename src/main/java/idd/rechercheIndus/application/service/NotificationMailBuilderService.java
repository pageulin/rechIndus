package idd.rechercheIndus.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import idd.rechercheIndus.application.entity.Mail;
import idd.rechercheIndus.application.entity.UserInfo;

@Service
public class NotificationMailBuilderService {

	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${mail.sender.address}")
	private String sender;
	@Value("${mail.signup.subject}")
	private String signupSubject;
	@Value("${mail.admin.address}")
	private String adminAddress;
	
	public Mail buildSignupMail(String recipient) {
		
		Mail mail = new Mail();
		mail.setMessage(templateEngine.process("mail/template.html", new Context()));
		mail.setRecipient(recipient);
		mail.setSubject(signupSubject);
		mail.setSender(sender);
		List<ClassPathResource> resources = new ArrayList<>();
		resources.add(new ClassPathResource("templates/mail/images/img-01.jpg"));
		mail.setAttachments(resources);
		return mail;
		
	}
	
	public Mail buildSignupMailAdmin(UserInfo userInfo) {
		Mail mail = new Mail();
		Context ctx = new Context();
		ctx.setVariable("mail", userInfo.getMail());
		ctx.setVariable("company", userInfo.getCompany());
		ctx.setVariable("questions", userInfo.getQuestions());
		mail.setMessage(templateEngine.process("mail/templateAdmin.html", ctx));
		mail.setRecipient(adminAddress);
		mail.setSubject(signupSubject);
		mail.setSender(sender);
		List<ClassPathResource> resources = new ArrayList<>();
		resources.add(new ClassPathResource("templates/mail/images/img-01.jpg"));
		mail.setAttachments(resources);
		return mail;
	}
	
}
