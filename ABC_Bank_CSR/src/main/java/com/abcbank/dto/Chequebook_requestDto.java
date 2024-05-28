package com.abcbank.dto;

public class Chequebook_requestDto {
	
	private String no_of_cheque_leaves;
	private String request_message;
	private int service_request_id;
	private Long accountNumber;

	
	public String getNo_of_cheque_leaves() {
		return no_of_cheque_leaves;
	}
	public void setNo_of_cheque_leaves(String no_of_cheque_leaves) {
		this.no_of_cheque_leaves = no_of_cheque_leaves;
	}
	public String getRequest_message() {
		return request_message;
	}
	public void setRequest_message(String request_message) {
		this.request_message = request_message;
	}
	public int getService_request_id() {
		return service_request_id;
	}
	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
