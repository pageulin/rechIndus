package idd.rechercheIndus.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idd.rechercheIndus.application.entity.UserAuth;
import idd.rechercheIndus.application.repository.UserAuthRepository;

@Service
public class UserAuthService {

	@Autowired
	private UserAuthRepository userAuthREpository;
	
	public Optional<UserAuth> findUserAuthByMailAndPassword(String mail, String password) {
		return userAuthREpository.findUserAuthByMailAndPassword(mail, password);
	}
	
}
