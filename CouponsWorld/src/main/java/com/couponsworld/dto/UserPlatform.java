package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UserPlatform {

	@Id
	long userPlatformId;
	String userPlatformName;

	public long getUserPlatformId() {
		return userPlatformId;
	}

	public void setUserPlatformId(long userPlatformId) {
		this.userPlatformId = userPlatformId;
	}

	public String getUserPlatformName() {
		return userPlatformName;
	}

	public void setUserPlatformName(String userPlatformName) {
		this.userPlatformName = userPlatformName;
	}

}
