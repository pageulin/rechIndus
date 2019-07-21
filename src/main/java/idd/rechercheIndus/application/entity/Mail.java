package idd.rechercheIndus.application.entity;

import java.util.List;

import org.springframework.core.io.ClassPathResource;

public class Mail {

	private String subject;
	private String message;
	private String recipient;
	private String sender;
	private List<ClassPathResource> attachments;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<ClassPathResource> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<ClassPathResource> attachments) {
		this.attachments = attachments;
	}
	
	
}
