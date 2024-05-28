package com.primesoftinc.message.customerRegister;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "BillerResDetails")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"billerId", "billerAmount","billerAccount","paymentReqId" })
public class BillerResDetails implements Serializable {

	private static final long serialVersionUID = 1997567336809085529L;
	
	
	@JsonProperty("BillerId")
	@XmlElement(name = "BillerId")
	public int billerId;
	
	@JsonProperty("BillerAmount")
	@XmlElement(name = "BillerAmount")
	public double billerAmount;
	
	@JsonProperty("BillerAccount")
	@XmlElement(name = "BillerAccount")
	public long billerAccount;
	
	@JsonProperty("PaymentReqId")
	@XmlElement(name = "PaymentReqId")
	public int paymentReqId;

	public int getBillerId() {
		return billerId;
	}

	public void setBillerId(int billerId) {
		this.billerId = billerId;
	}

	public double getBillerAmount() {
		return billerAmount;
	}

	public void setBillerAmount(double billerAmount) {
		this.billerAmount = billerAmount;
	}

	public long getBillerAccount() {
		return billerAccount;
	}

	public void setBillerAccount(long billerAccount) {
		this.billerAccount = billerAccount;
	}

	public int getPaymentReqId() {
		return paymentReqId;
	}

	public void setPaymentReqId(int paymentReqId) {
		this.paymentReqId = paymentReqId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public BillerResDetails(int billerId, double billerAmount, long billerAccount, int paymentReqId) {
//		super();
//		this.billerId = billerId;
//		this.billerAmount = billerAmount;
//		this.billerAccount = billerAccount;
//		this.paymentReqId = paymentReqId;
//	}
//
//	public BillerResDetails() {
//		super();
//	}
//	
//	
	
	
	
}
