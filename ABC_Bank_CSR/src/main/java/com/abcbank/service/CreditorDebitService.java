package com.abcbank.service;

import com.abcbank.dto.CreditdebitDto;

import jakarta.xml.bind.JAXBException;

public interface CreditorDebitService {

//	public Object saveCreditordebit(CreditdebitDto creditdebitDto);

	public Object getAllCreditordebit_request();

	public Object getbyid(int cDid);

	public Object saverequest(CreditdebitDto creditrequest) throws JAXBException, InstantiationException, IllegalAccessException ;


}
