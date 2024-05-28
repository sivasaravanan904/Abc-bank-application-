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
@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"processId", "responseDate","responseMessage","requestId","status","serviceReqId","cardNumber" })
public class Response implements Serializable{
	
	private static final long serialVersionUID = 1997567336809085529L;
	
	@JsonProperty("ProcessId")
	@XmlElement(name = "ProcessId")
	public int processId;
	
	@JsonProperty("ResponseDate")
	@XmlElement(name = "ResponseDate")
	public Date responseDate;
	
	@JsonProperty("ResponseMessage")
	@XmlElement(name = "ResponseMessage")
	public String responseMessage;
	
	@JsonProperty("RequestId")
	@XmlElement(name = "RequestId")
	public int requestId;
	
	@JsonProperty("Status")
	@XmlElement(name = "Status")
	public String status;
	
	@JsonProperty("ServiceReqId")
	@XmlElement(name = "ServiceReqId")
	public int serviceReqId;
	
	@JsonProperty("CardNumber")
	@XmlElement(name = "CardNumber")
	public long cardNumber;

	public int getServiceReqId() {
		return serviceReqId;
	}

	public void setServiceReqId(int serviceReqId) {
		this.serviceReqId = serviceReqId;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public Response(int processId, Date responseDate, String responseMessage, int requestId, String status,int serviceReqId) {
//		super();
//		this.processId = processId;
//		this.responseDate = responseDate;
//		this.responseMessage = responseMessage;
//		this.requestId = requestId;
//		this.status = status;
//		this.serviceReqId = serviceReqId;
//	}
//
//	public Response() {
//		super();
//	}
//	
	

}
