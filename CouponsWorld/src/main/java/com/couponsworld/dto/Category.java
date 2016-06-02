package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Category {

	@Id
	private long categoryId;
	private String categoryName;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
