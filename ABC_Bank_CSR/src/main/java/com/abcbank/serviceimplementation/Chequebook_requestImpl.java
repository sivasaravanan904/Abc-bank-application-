package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.config.RabbitMQConfig;
import com.abcbank.dto.Chequebook_requestDto;
import com.abcbank.model.Account;
import com.abcbank.model.Chequebook_request;
import com.abcbank.model.Service_request;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.Chequebook_requestRepo;
import com.abcbank.repository.ServiceRequestRepo;
import com.abcbank.service.Chequebook_requestService;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;
import jakarta.xml.bind.JAXBException;
@Service
public class Chequebook_requestImpl implements Chequebook_requestService{
	
	@Autowired
	private Chequebook_requestRepo chequerepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ServiceRequestRepo serviceRequestRepo;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@Override
	public Object getAllCards() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List <Chequebook_request> chequebookRequest  = chequerepo.findAll();
		for(Chequebook_request e:chequebookRequest) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("chequebook_request_id", e.getChequebook_request_id());
			map.put("number_of_cheque_leaves", e.getNo_of_cheque_leaves());
			map.put("request_date", e.getRequest_date());
			map.put("response_date", e.getResponse_date());
			map.put("response_message", e.getResponse_message());
			map.put("request_message", e.getRequest_message());
			map.put("response_status", e.getResponse_status());
//			map.put("service_request_id", e.getService_request_id());
			map.put("process_id", e.getProcess_id());
//			map.put("css_request_id", e.getCss_request_id());
			
			list.add(map);

		}
		return list;
	}


	@Override
	public Object getcardById(int id) {
		Map<String, Object> map=new HashMap<String, Object>();
		Chequebook_request chequebookRequest = chequerepo.findById(id).orElse(null);
		if(chequebookRequest!=null) {
			map.put("chequebook_request_id", chequebookRequest.getChequebook_request_id());
			map.put("number_of_cheque_leaves", chequebookRequest.getNo_of_cheque_leaves());
			map.put("request_date", chequebookRequest.getRequest_date());
			map.put("response_date", chequebookRequest.getResponse_date());
			map.put("response_message", chequebookRequest.getResponse_message());
			map.put("request_message", chequebookRequest.getRequest_message());
			map.put("response_status", chequebookRequest.getResponse_status());
			map.put("process_id", chequebookRequest.getProcess_id());
			
//			map.put("service_request_id", chequebookRequest.getService_request_id());
//			map.put("css_request_id", chequebookRequest.getCss_request_id());
			return map;
		}
		return map;
	}



	@Override
	public Object saveCheque(Chequebook_request chequebookRequest)
		throws  JAXBException,InstantiationException,IllegalAccessException{
		int Chequebook_request_id = chequebookRequest.getChequebook_request_id();
		Map<String, Object> map=new HashMap<String, Object>();
		
		if(chequebookRequest.getRequest_message().isEmpty()) {
			map.put("status", "error");
			map.put("msg", "Please enter the Request message :");
		}
		else if(chequebookRequest.getRequest_date()== null) {
			map.put("status", "error");
			map.put("msg", "Please enter the Request Date :");
		}
		
		
		else if (chequebookRequest.getNo_of_cheque_leaves().isEmpty()) {
			map.put("status", "error");
			map.put("msg", "Please enter the Number of Leaves in chequeBook :");
		}
		else if(chequebookRequest.getResponse_date()== null) {
			map.put("status", "error");
			map.put("msg", "Please enter the Response Date :");
		}	
		
		else if(chequebookRequest.getResponse_message().isEmpty()) {
			map.put("status", "error");
			map.put("msg", "Please enter the Response Message:");
		}
//	else if(chequebookRequest.getCss_request_id()==0) {
//			map.put("status", "error");
//			map.put("msg", "Please enter the CSS Request Id:");
//		}
		else if(chequebookRequest.getProcess_id()==0) {
			map.put("status", "error");
			map.put("msg", "Please enter the Process Id:");
		}
		
		else if(chequebookRequest.getResponse_status().isEmpty()) {
			map.put("status", "error");
			map.put("msg", "Please enter the Response status:");
		}
		else {
			chequerepo.save(chequebookRequest);
			map.put("status", "Success");
			map.put("msg", "Data saved Successfully :");
		}
		return map;
	}


	@Override
	public Object saverequest(Chequebook_requestDto chequeBookDTO) throws JAXBException, InstantiationException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (chequeBookDTO.getNo_of_cheque_leaves().isEmpty() || chequeBookDTO.getNo_of_cheque_leaves() == null) {
			map.put("status", "error");
			map.put("msg", "give the valid numbers");
		}

		else if (chequeBookDTO.getAccountNumber() == 0) {
			map.put("status", "error");
			map.put("msg", "give the valid accountNumber");
		} else if (chequeBookDTO.getService_request_id() == 0) {
			map.put("status", "error");
			map.put("msg", "give the valid requesttypeid");
		} 
//		else {
//
//			Chequebook_request chequebook_request=chequerepo.findbydateandaccountNumber(new Date(),chequeBookDTO.getAccountNumber());
//			System.out.println("Date "+ chequebook_request);
//			if(chequebook_request ==null)
//			{
//				map.put("status", "error");
//				map.put("msg", "request is already present");
//			}
			else
			{
			Chequebook_request chequeBook = new Chequebook_request();
			chequeBook.setNo_of_cheque_leaves(chequeBookDTO.getNo_of_cheque_leaves());
			Account myAccount = accountRepo.findById(chequeBookDTO.getAccountNumber()).orElse(null);
			chequeBook.setAccount(myAccount);
			chequeBook.setRequest_date(new Date());
			Service_request serviceRequest1 = serviceRequestRepo.findById(chequeBookDTO.getService_request_id())
					.orElse(null);

			chequeBook.setRequest(serviceRequest1);
			chequeBook.setRequest_message(chequeBookDTO.getRequest_message());
			chequeBook.setResponse_status("Pending");
			Chequebook_request creditrequest = chequerepo.save(chequeBook);
			map.put("status", "success");
			map.put("msg", "data saved successfully");

			String ReqTemplate = RequestTemplate.RequestXMLTemplate;
			com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService
			.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
			serRequest.setCustomerId(myAccount.getCustomer().getCustomer_id());
			serRequest.setAccountNumber(myAccount.getAccount_number());
			serRequest.getRequestType().setRequestDate(new Date());
			serRequest.getRequestType().setRequestId(creditrequest.getChequebook_request_id());
			serRequest.getRequestType().setRequestMessage(chequeBookDTO.getRequest_message());
			serRequest.getRequestType().setTypeOfRequest(1);
			serRequest.getRequestType().setRequestData(chequeBookDTO.getNo_of_cheque_leaves());
			String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class,serRequest);
			System.out.println("/////////" + xml);
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTINGKEYSENDER, xml);
			}
		
		return map;
	}

	 private int randomNumberGeneration() {
		 Random rnd = new Random();
		    int number = rnd.nextInt(99999);
		 return number;
		 }


	

}
