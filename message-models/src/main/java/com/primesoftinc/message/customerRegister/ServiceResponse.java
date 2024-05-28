package com.primesoftinc.message.customerRegister;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "ServiceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceResponse implements Serializable {
	
	private static final long serialVersionUID = 1997567336809085529L;
	
	@JsonProperty("CustomerId")
	@XmlElement(name = "CustomerId")
	public int customerId;
	
	@JsonProperty("AccountNumber")
	@XmlElement(name = "AccountNumber")
	public Long accountNumber;
	
	
	@JsonProperty("Response")
	@XmlElement(name = "Response")
	public Response response;


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Response getResponse() {
		return response;
	}


	public void setResponse(Response response) {
		this.response = response;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


//	public ServiceResponse(int customerId, Long accountNumber, Response response) {
//		super();
//		this.customerId = customerId;
//		this.accountNumber = accountNumber;
//		this.response = response;
//	}
//
//
//	public ServiceResponse() {
//		super();
//	}
//	
	

}
