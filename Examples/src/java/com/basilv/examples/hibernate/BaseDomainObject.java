package com.basilv.examples.hibernate;

import java.sql.Timestamp;
/*
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
*/public class BaseDomainObject implements Auditable {

//	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
//	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
//	@Column(name = "CREATE_TIMESTAMP")
	private Timestamp createTimestamp;
	
//	@Column(name = "UPDATE_TIMESTAMP")
	private Timestamp updateTimestamp;

	
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	
}
