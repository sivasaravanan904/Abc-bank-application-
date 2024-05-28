package com.abcbank.service;

import com.abcbank.dto.LostorStolenDto;
import com.abcbank.model.Lostorstolen_request;

import jakarta.xml.bind.JAXBException;

public interface LostorstolenRequestService {


	public Object getbycustomerid(int customer_id);

	public Object savelostcard(LostorStolenDto dtos)throws InstantiationException, IllegalAccessException, JAXBException ;

}
