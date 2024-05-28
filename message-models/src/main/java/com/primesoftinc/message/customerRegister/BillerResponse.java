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
@XmlRootElement(name = "BillerResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillerResponse implements Serializable {
	
	private static final long serialVersionUID = 1997567336809085529L;
	
	@JsonProperty("CustomerId")
	@XmlElement(name = "CustomerId")
	public int customerId;
	
	@JsonProperty("AccountNo")
	@XmlElement(name = "AccountNo")
	public long accountNo;
	
	@JsonProperty("BillerResDetails")
	@XmlElement(name = "BillerResDetails")
	public BillerResDetails billerResDetails;
	
	
	@JsonProperty("Status")
	@XmlElement(name = "Status")
	public String status;
	
	@JsonProperty("Remarks")
	@XmlElement(name = "Remarks")
	public String remarks;

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

	public BillerResDetails getBillerResDetails() {
		return billerResDetails;
	}

	public void setBillerResDetails(BillerResDetails billerResDetails) {
		this.billerResDetails = billerResDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public BillerResponse(int customerId, long accountNo, BillerResDetails billerResDetails, String status,
//			String remarks) {
//		super();
//		this.customerId = customerId;
//		this.accountNo = accountNo;
//		this.billerResDetails = billerResDetails;
//		this.status = status;
//		this.remarks = remarks;
//	}
//
//	public BillerResponse() {
//		super();
//	}
//	
	
	

}
