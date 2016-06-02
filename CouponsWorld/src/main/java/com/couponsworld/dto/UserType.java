package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UserType {

	@Id
	private long userTypeId;
	private String userTypeName;

	public long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

}
