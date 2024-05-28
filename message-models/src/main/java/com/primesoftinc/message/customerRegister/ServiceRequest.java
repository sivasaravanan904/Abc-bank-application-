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
@XmlRootElement(name = "ServiceRequest")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"customerId", "accountNumber","requestType" })
public class ServiceRequest  implements Serializable{
	
	private static final long serialVersionUID = 1997567336809085529L;
	
	@JsonProperty("CustomerId")
	@XmlElement(name = "CustomerId")
	public int customerId;
	
	@JsonProperty("AccountNumber")
	@XmlElement(name = "AccountNumber")
	public Long accountNumber;
	
	@JsonProperty("RequestType")
	@XmlElement(name = "RequestType")
	public RequestType requestType;

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

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public ServiceRequest(int customerId, Long accountNumber, RequestType requestType) {
//		super();
//		this.customerId = customerId;
//		this.accountNumber = accountNumber;
//		this.requestType = requestType;
//	}
//
//	public ServiceRequest() {
//		super();
//	}
//	
	
}
