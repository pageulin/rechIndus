package idd.rechercheIndus.application.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAuth {

	@Id
	private Long id;
	
	private String mail;
	
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
