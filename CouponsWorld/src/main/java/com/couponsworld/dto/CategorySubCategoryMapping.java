package com.couponsworld.dto;

import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CategorySubCategoryMapping {

	@Id
	private long categorySubCategoryMappingId;
	@Index
	private String categoryName;
	@Index
	private String subCategoryNames; // subcategory names seperated by ","

	public long getCategorySubCategoryMappingId() {
		return categorySubCategoryMappingId;
	}

	public void setCategorySubCategoryMappingId(long categorySubCategoryMappingId) {
		this.categorySubCategoryMappingId = categorySubCategoryMappingId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryNames() {
		return subCategoryNames;
	}

	public void setSubCategoryNames(String subCategoryNames) {
		this.subCategoryNames = subCategoryNames;
	}

}
