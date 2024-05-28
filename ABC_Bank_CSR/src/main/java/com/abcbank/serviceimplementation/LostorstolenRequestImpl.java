package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.config.RabbitMQConfig;
import com.abcbank.dto.LostorStolenDto;
import com.abcbank.model.Account;
import com.abcbank.model.Lostorstolen_request;
import com.abcbank.model.Service_request;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.LostorstolenRequestRepo;
import com.abcbank.repository.ServiceRequestRepo;
import com.abcbank.service.LostorstolenRequestService;
import com.primesoftinc.message.customerRegister.ServiceRequest;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;

import jakarta.xml.bind.JAXBException;

@Service
public class LostorstolenRequestImpl implements LostorstolenRequestService{
	
	@Autowired
	private LostorstolenRequestRepo lostorstolen_requestRepo;

	@Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ServiceRequestRepo service_requestRepo;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
		@Override
	    public Object getbycustomerid(int customer_id) {
	        List<Map<String, Object>>list=new ArrayList<>();
	        List<Account>account=accountRepo.findBycustomerId(customer_id);
	        if(account!=null)
	        {
	            for(Account  e:account)
	            {
	                Map<String,Object>map=new HashMap<>();
	                map.put("accountnumber", e.getAccount_number());
	                list.add(map);
	            }
	        }
	        else
	        {
	            Map<String,Object>map=new HashMap<>();
	            map.put("status", "error");
	            map.put("msg","invalid id");
	            list.add(map);
	        }
	        return list;
	    }


		@Override
		public Object savelostcard(LostorStolenDto dtos) throws InstantiationException, IllegalAccessException, JAXBException {
			
			
			Map<String,Object>map=new HashMap<>();
			if(dtos.getService_request_id()==0)
			{
			map.put("status","error");
			map.put("msg","invalid service requestid");
			}
			else if(dtos.getAccount_number()==0)
			{
			map.put("status","error");
			map.put("msg","invalid account number");

			}
			else if(dtos.getRequest_message().isEmpty()||dtos.getRequest_message()==null)
			{
			map.put("status","error");
			map.put("msg","invalid request Message");

			}
			// else if(dtos.getStolenDate().toString().isEmpty()||dtos.getStolenDate()==null) {
			// map.put("status", "error");
			// map.put("msg", "invalid stolen date");
			// }
			else if(dtos.getCard_type().isEmpty()||dtos.getCard_type()==null) {
			map.put("status", "error");
			map.put("msg", "invalid card type");
			}
//			else if(dtos.getCard_number()==0) {
//			map.put("status", "error");
//			map.put("msg", "invalid cardnumber");
//			}
			else
			{
			Lostorstolen_request lostStolenCard= new Lostorstolen_request();
			Account myAccount =  accountRepo.findById(dtos.getAccount_number()).orElse(null);
			lostStolenCard.setCard_type(dtos.getCard_type());
			lostStolenCard.setAccount(myAccount);
			System.out.println("////////"+myAccount);

			lostStolenCard.setCard_number(dtos.getCard_number());
			lostStolenCard.setRequest_date(new Date());
			lostStolenCard.setLost_stolen_date(dtos.getLost_stolen_date());
			Service_request serviceRequest =  service_requestRepo.findById(dtos.getService_request_id()).orElse(null);
			lostStolenCard.setRequest(serviceRequest);
			lostStolenCard.setResponse_status("Pending");
			lostStolenCard.setRequest_message(dtos.getRequest_message());
			lostStolenCard.setLost_stolen_date(dtos.getLost_stolen_date());
			Lostorstolen_request crdDebCard =lostorstolen_requestRepo.save(lostStolenCard);
			map.put("status","success");
			map.put("msg","data saved successfully");

			String ReqTemplate = RequestTemplate.RequestXMLTemplate;
			com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
			serRequest.setCustomerId(myAccount.getCustomer().getCustomer_id());
			System.out.println(myAccount.getCustomer().getCustomer_id());
			serRequest.setAccountNumber(myAccount.getAccount_number());
			serRequest.getRequestType().setRequestDate(new Date());
			//serRequest.getRequestType().setStolenDate(crdDebCard.getStolen_date());
			serRequest.getRequestType().setRequestDate(dtos.getLost_stolen_date());
//			   serRequest.getRequestType().set
			  serRequest.getRequestType().setRequestId(dtos.getService_request_id());
			serRequest.getRequestType().setRequestMessage(dtos.getRequest_message());
			
			serRequest.getRequestType().setRequestData(dtos.getCard_type());
			serRequest.getRequestType().setTypeOfRequest(3);
			//serRequest.getRequestType().setStolenDate(dtos.getStolenDate());
			String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class, serRequest);
			System.out.println("/////////" + xml);

			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEYSENDER,xml);

			}
			return map;
			}
	
}
