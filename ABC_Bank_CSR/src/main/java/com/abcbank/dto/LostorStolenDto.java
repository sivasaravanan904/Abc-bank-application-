package com.abcbank.dto;

import java.util.Date;

public class LostorStolenDto {
	
	private int service_request_id;
    private long account_number;
    private String request_message;
	private Date lost_stolen_date;
    private long card_number;
    private String card_type;

    
    public LostorStolenDto(int service_request_id, long account_number, String request_message, Date lost_stolen_date,
			long card_number, String card_type) {
		super();
		this.service_request_id = service_request_id;
		this.account_number = account_number;
		this.request_message = request_message;
		this.lost_stolen_date = lost_stolen_date;
		this.card_number = card_number;
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
	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}
	public String getRequest_message() {
		return request_message;
	}
	public void setRequest_message(String request_message) {
		this.request_message = request_message;
	}
	public Date getLost_stolen_date() {
		return lost_stolen_date;
	}
	public void setLost_stolen_date(Date lost_stolen_date) {
		this.lost_stolen_date = lost_stolen_date;
	}
	public long getCard_number() {
		return card_number;
	}
	public void setCard_number(long card_number) {
		this.card_number = card_number;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}


   
}
