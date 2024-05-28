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
@XmlRootElement(name = "CustomerRegister")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "userName","dob", "phoneNo","address","city","pinCode","country","panNo","email" })
public class CustomerRegister implements Serializable {
	
	private static final long serialVersionUID = 1997567336809085529L;
	@JsonProperty("Name")
	@XmlElement(name = "Name")
	public String name;
	
	@JsonProperty("UserName")
	@XmlElement(name = "UserName")
	public String userName;
	
	@JsonProperty("Dob")
	@XmlElement(name = "Dob")
	public String dob;
	
	@JsonProperty("PhoneNo")
	@XmlElement(name = "PhoneNo")
	public String phoneNo;
	
	@JsonProperty("Address")
	@XmlElement(name = "Address")
	public String address;
	
	@JsonProperty("City")
	@XmlElement(name = "City")
	public String city;
	
	@JsonProperty("PinCode")
	@XmlElement(name = "PinCode")
	public String pinCode;
	
	@JsonProperty("Country")
	@XmlElement(name = "Country")
	public String country;
	
	@JsonProperty("PanNo")
	@XmlElement(name = "PanNo")
	public String panNo;
	
	@JsonProperty("Email")
	@XmlElement(name = "Email")
	public String email;

}
