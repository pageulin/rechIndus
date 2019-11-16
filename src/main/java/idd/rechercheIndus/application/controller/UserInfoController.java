package idd.rechercheIndus.application.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import idd.rechercheIndus.application.entity.UserInfo;
import idd.rechercheIndus.application.exception.InvalidDataException;
import idd.rechercheIndus.application.service.NotificationMailBuilderService;
import idd.rechercheIndus.application.service.SendGridMailSenderService;
import javassist.NotFoundException;

@RestController
public class UserInfoController {

	@Autowired
	private SendGridMailSenderService mailService;
	@Autowired
	private NotificationMailBuilderService mailBuilderService;
	
	@PostMapping("/signup")
	public UserInfo signup(@RequestBody UserInfo userInfo) throws NotFoundException, MessagingException {
		if(userInfo == null) {
			throw new InvalidDataException("");
		}
		if(userInfo.getMail() == null || userInfo.getMail().isEmpty()) {
			throw new InvalidDataException("L'adresse mail est obligatoire");
		}
		mailService.sendMail(mailBuilderService.buildSignupMailAdmin(userInfo));
		return userInfo;
	}
	
	
}
