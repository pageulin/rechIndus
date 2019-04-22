package idd.rechercheIndus.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idd.rechercheIndus.application.entity.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

	public Optional<UserAuth> findUserAuthByMailAndPassword(String mail, String password);
	
}
