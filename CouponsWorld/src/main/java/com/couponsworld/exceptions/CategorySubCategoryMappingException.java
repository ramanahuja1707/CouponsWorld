package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class CategorySubCategoryMappingException extends Exception {
	String errorMessage;

	public CategorySubCategoryMappingException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}
}
