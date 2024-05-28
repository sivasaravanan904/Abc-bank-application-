package com.abcbank.service;

import com.abcbank.dto.Chequebook_requestDto;
import com.abcbank.model.Chequebook_request;

import jakarta.xml.bind.JAXBException;

public interface Chequebook_requestService {

	public Object getAllCards();
	public Object getcardById(int id);
	public Object saveCheque(Chequebook_request chequebookRequest) throws JAXBException, InstantiationException, IllegalAccessException;
//	public Object saverequest(Chequebook_requestDto chequeBookDTO)throws JAXBException, InstantiationException, IllegalAccessException;
//	public Object getcardById(int service_request_id, int chequebook_request_id);
//	public Object updateById(Chequebook_requestDto chequeBookRequest);
	public Object saverequest(Chequebook_requestDto chequeBookDTO) throws JAXBException , InstantiationException, IllegalAccessException;

}

