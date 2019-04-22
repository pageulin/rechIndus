package idd.rechercheIndus.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idd.rechercheIndus.application.entity.UserInfo;
import idd.rechercheIndus.application.repository.UserInfoRepository;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public Optional<UserInfo> findUserById(Long id) {
		return userInfoRepository.findById(id);
	}
	
	public Optional<UserInfo> findUserByMail(String mail) {
		return userInfoRepository.findUserInfoByMail(mail);
	}
	
	public UserInfo save(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}
	
}
