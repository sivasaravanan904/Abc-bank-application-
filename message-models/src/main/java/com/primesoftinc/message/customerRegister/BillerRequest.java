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
@XmlRootElement(name = "BillerRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillerRequest implements Serializable {
private static final long serialVersionUID = 1997567336809085529L;
	
	@JsonProperty("CustomerId")
	@XmlElement(name = "CustomerId")
	public int customerId;
	
	@JsonProperty("AccountNo")
	@XmlElement(name = "AccountNo")
	public long accountNo;
	
	@JsonProperty("BillerReqDetails")
	@XmlElement(name = "BillerReqDetails")
	public BillerReqDetails billerReqDetails;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public BillerReqDetails getBillerReqDetails() {
		return billerReqDetails;
	}

	public void setBillerReqDetails(BillerReqDetails billerReqDetails) {
		this.billerReqDetails = billerReqDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public BillerRequest(int customerId, long accountNo, BillerReqDetails billerReqDetails) {
//		super();
//		this.customerId = customerId;
//		this.accountNo = accountNo;
//		this.billerReqDetails = billerReqDetails;
//	}
//
//	public BillerRequest() {
//		super();
//	}
//	
	
}
