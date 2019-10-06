package idd.rechercheIndus.application.controller;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import idd.rechercheIndus.application.entity.UserInfo;
import idd.rechercheIndus.application.exception.InvalidDataException;
import idd.rechercheIndus.application.exception.UserInfoNotFoundException;
import idd.rechercheIndus.application.service.NotificationMailBuilderService;
import idd.rechercheIndus.application.service.SendGridMailSenderService;
import idd.rechercheIndus.application.service.UserInfoService;
import javassist.NotFoundException;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SendGridMailSenderService mailService;
	@Autowired
	private NotificationMailBuilderService mailBuilderService;
	
	@PostMapping("/login")
	public UserInfo login(@RequestBody UserInfo userInfo) throws NotFoundException {
		if(userInfo == null) {
			throw new UserInfoNotFoundException("");
		}
		String mail = userInfo.getMail();
		if(mail == null) {
			throw new UserInfoNotFoundException("");
		}
		Optional<UserInfo> userInfoOpt = userInfoService.findUserByMail(mail);
		if(userInfoOpt.isPresent()) {
			if(userInfoOpt.get().isEnabled()) {
				return userInfoOpt.get();
			}
			else {
				return null;
			}
		}
		else {
			throw new UserInfoNotFoundException("");
		}
	}
	
	@PostMapping("/signup")
	public UserInfo signup(@RequestBody UserInfo userInfo) throws NotFoundException, MessagingException {
		if(userInfo == null) {
			throw new InvalidDataException("");
		}
		if(userInfo.getMail() == null || userInfo.getMail().isEmpty()) {
			throw new InvalidDataException("L'adresse mail est obligatoire");
		}
		Optional<UserInfo> userInfoOpt = userInfoService.findUserByMail(userInfo.getMail());
		UserInfo newUserInfo = null;
		if(userInfoOpt.isPresent()) {
			UserInfo existingUserInfo = userInfoOpt.get();
			existingUserInfo.setQuestions(existingUserInfo.getQuestions() + "\n" + userInfo.getQuestions());
			newUserInfo = userInfoService.save(existingUserInfo);
		}
		else {
			newUserInfo = userInfoService.save(userInfo);
		}
		if(newUserInfo != null) {
			mailService.sendMail(mailBuilderService.buildSignupMail(newUserInfo.getMail()));
			return newUserInfo;
		}
		else {
			throw new InvalidDataException("");
		}
	}
	
	
}
