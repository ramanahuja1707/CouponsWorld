package com.couponsworld.dto;

public class Link {

	private String href = null;
	private String rel = null;
	private String method = null;
	private String contentTypeConsumes = null;
	private String contentTypeProduces = null;

	public String getContentTypeConsumes() {
		return contentTypeConsumes;
	}

	public void setContentTypeConsumes(String contentTypeConsumes) {
		this.contentTypeConsumes = contentTypeConsumes;
	}

	public String getContentTypeProduces() {
		return contentTypeProduces;
	}

	public void setContentTypeProduces(String contentTypeProduces) {
		this.contentTypeProduces = contentTypeProduces;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
