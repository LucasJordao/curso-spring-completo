package com.lucaswilliam.cursospringcompleto.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Integer status;
	private String msg;
	private Long timeStamp;
	private String uri;
	
	//Constructors and Overloads
	public StandardError(Integer status, String msg, Long timeStamp, String uri) {
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
		this.uri = uri;
	}
	
	//Getters and Setters
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
