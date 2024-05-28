package com.abcbank.dto;

import java.io.Serializable;

public class ServicerequestDto implements Serializable{
	
	//private static final long serialVersionUID = 8336749435408801262L;
	private int service_request_id;
//	private String status;
	private Long account_number;
	
	public Long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(Long account_number) {
		this.account_number = account_number;
	}
	public int getService_request_id() {
		return service_request_id;
	}
	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	
	
	

}
