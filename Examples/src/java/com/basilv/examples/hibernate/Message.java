package com.basilv.examples.hibernate;

/*
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(
	selectBeforeUpdate = false
 )
@Table(name = "MESSAGES")
*/
public class Message extends BaseDomainObject {
	
//	@Id
//	@GeneratedValue
//	@Column(name = "MESSAGE_ID")
	private Long id;

//	@Column(name = "MESSAGE_TEXT")
	private String text;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "NEXT_MESSAGE_ID")
	private Message nextMessage;

	@SuppressWarnings("unused") 
	private Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
}
