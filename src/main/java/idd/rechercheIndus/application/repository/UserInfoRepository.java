package idd.rechercheIndus.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idd.rechercheIndus.application.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	public Optional<UserInfo> findUserInfoByMail(String mail);
	
}
