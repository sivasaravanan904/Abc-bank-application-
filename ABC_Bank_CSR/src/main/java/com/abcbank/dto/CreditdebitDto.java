package com.abcbank.dto;

import java.io.Serializable;

public class CreditdebitDto implements Serializable{
	
	private String card_type;

	private int service_request_id;

	private long account_number;

	private String request_message;

	

	

	public String getCard_type() {

		return card_type;

	}

	public void setCard_type(String card_type) {

		this.card_type = card_type;

	}

	public int getService_request_id() {

		return service_request_id;

	}

	public void setService_request_id(int service_request_id) {

		this.service_request_id = service_request_id;

	}

	public long getAccount_number() {

		return account_number;

	}

	public void setAccount_number(Long account_number) {

		this.account_number = account_number;

	}

	public String getRequest_message() {

		return request_message;

	}

	public void setRequest_message(String request_message) {

		this.request_message = request_message;

	}

	public CreditdebitDto(String card_type, int service_request_id, Long account_number, String request_message) {

		super();

		this.card_type = card_type;

		this.service_request_id = service_request_id;

		this.account_number = account_number;

		this.request_message = request_message;

	}

}
