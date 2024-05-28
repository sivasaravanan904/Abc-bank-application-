package com.abcbank.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbank.config.RabbitMQConfig;
import com.abcbank.model.Chequebook_request;
import com.abcbank.model.Creditordebit_request;
import com.abcbank.model.Lostorstolen_request;
import com.abcbank.repository.Chequebook_requestRepo;
import com.abcbank.repository.CreditDebitRequestRepo;
import com.abcbank.repository.LostorstolenRequestRepo;
import com.primesoftinc.message.customerRegister.ServiceResponse;
import com.primesoftinc.message.service.CoreService;

import jakarta.xml.bind.JAXBException;

@Component
public class RabbitMQListener {
	
	@Autowired
	RabbitTemplate template;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private Chequebook_requestRepo chequeBookRequestRepo;
	
	
	@Autowired
	private CreditDebitRequestRepo cardRequestRepo;
	
	
	@Autowired
	private LostorstolenRequestRepo    lostStolenCardRepo;
	
	
	@RabbitListener(queues = RabbitMQConfig.RECIVERQUEUE)
	public void onMessage(String msg) throws JAXBException
	{
		if(msg !=null)
		{
			ServiceResponse serResponse = (ServiceResponse) coreService.unmarshal(msg, ServiceResponse.class);		
		
			int typeOfRequest = serResponse.getResponse().getServiceReqId();
			System.out.println("typeOfRequest"+typeOfRequest);
			
			if(typeOfRequest ==1)
			{
			Chequebook_request chequeBookRequest= chequeBookRequestRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
	//System.out.println("??????????"+chequeBookRequest.getChequebookRequestId());
			if(chequeBookRequest != null)
			{
				//chequeBookRequest.setResponse_status(serResponse.getResponse());
				chequeBookRequest.setProcess_id(serResponse.getResponse().getProcessId());
				chequeBookRequest.setResponse_message(serResponse.getResponse().getResponseMessage());
				chequeBookRequest.setResponse_date(serResponse.getResponse().getResponseDate());
				chequeBookRequestRepo.saveAndFlush(chequeBookRequest);
			}
			}

			 else if(typeOfRequest ==2)
			{
			Creditordebit_request crdDebCardReq = cardRequestRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
			System.out.println("//////////////////////////////////"+crdDebCardReq);
			if(crdDebCardReq != null)
			{
				crdDebCardReq.setResponse_status(serResponse.getResponse().getStatus());
				crdDebCardReq.setProcess_id(serResponse.getResponse().getProcessId());
				crdDebCardReq.setResponse_message(serResponse.getResponse().getResponseMessage());
				crdDebCardReq.setResponse_date(serResponse.getResponse().getResponseDate());
//				crdDebCardReq.setCard_number(serResponse.getResponse().getCardNumber());
				cardRequestRepo.saveAndFlush(crdDebCardReq);
			}
			}
			else if(typeOfRequest ==3)
			{
				System.out.println("///////////"+msg);
				Lostorstolen_request lost_stolen_card_request=lostStolenCardRepo.findById(serResponse.getResponse().getRequestId()).orElse(null);
			//	System.out.println("22222222222222222222"+ lost_stolen_card_request.getStolen_request_id()+"??????????????");
				if(lost_stolen_card_request != null)
				{
					lost_stolen_card_request.setResponse_status(serResponse.getResponse().getStatus());
					lost_stolen_card_request.setProcess_id(serResponse.getResponse().getProcessId());
					lost_stolen_card_request.setResponse_message(serResponse.getResponse().getResponseMessage());
					lost_stolen_card_request.setResponse_date(serResponse.getResponse().getResponseDate());
					lostStolenCardRepo.saveAndFlush(lost_stolen_card_request);
				}
			}
		
	
	}
}
}
