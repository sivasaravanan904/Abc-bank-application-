package com.primesoftinc.message.customerRegister;

import java.io.Serializable;
import java.util.Date;

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
@XmlRootElement(name = "RequestType")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"typeOfRequest", "requestDate","requestMessage","requestId","requestData","cardNumber","cardType","stolenDate"})
public class RequestType implements Serializable{
	private static final long serialVersionUID = 1997567336809085529L;

	@JsonProperty("TypeOfRequest")
	@XmlElement(name = "TypeOfRequest")
	public int typeOfRequest;
	
	@JsonProperty("RequestDate")
	@XmlElement(name = "RequestDate")
	public Date requestDate;
	
	@JsonProperty("RequestMessage")
	@XmlElement(name = "RequestMessage")
	public String requestMessage;
	
	@JsonProperty("RequestId")
	@XmlElement(name = "RequestId")
	public int requestId;
	
	@JsonProperty("RequestData")
	@XmlElement(name = "RequestData")
	public String requestData;
	
	@JsonProperty("CardNumber")
	@XmlElement(name = "CardNumber")
	public long cardNumber;
	
	@JsonProperty("CardType")
	@XmlElement(name="CardType")
	public String cardType;
	
	@JsonProperty("StolenDate")
	@XmlElement(name = "StolenDate")
	public Date stolenDate;

	public int getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(int typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public RequestType(int typeOfRequest, Date requestDate, String requestMessage, int requestId, String requestData) {
//		super();
//		this.typeOfRequest = typeOfRequest;
//		this.requestDate = requestDate;
//		this.requestMessage = requestMessage;
//		this.requestId = requestId;
//		this.requestData = requestData;
//	}
//
//	public RequestType() {
//		super();
//	}
//	
	
}
