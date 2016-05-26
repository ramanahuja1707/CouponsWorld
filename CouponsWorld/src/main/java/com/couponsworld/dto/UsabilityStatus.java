package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UsabilityStatus {

	@Id
	long usabilityStatusId;
	String usabilityStatusName;

	public long getUsabilityStatusId() {
		return usabilityStatusId;
	}

	public void setUsabilityStatusId(long usabilityStatusId) {
		this.usabilityStatusId = usabilityStatusId;
	}

	public String getUsabilityStatusName() {
		return usabilityStatusName;
	}

	public void setUsabilityStatusName(String usabilityStatusName) {
		this.usabilityStatusName = usabilityStatusName;
	}

}
