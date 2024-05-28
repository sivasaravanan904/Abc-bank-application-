package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.abcbank.config.RabbitMQConfig;
import com.abcbank.dto.CreditdebitDto;
import com.abcbank.model.Account;
import com.abcbank.model.Creditordebit_request;
import com.abcbank.model.Service_request;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.CreditDebitRequestRepo;
import com.abcbank.repository.ServiceRequestRepo;
import com.abcbank.service.CreditorDebitService;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.RequestTemplate;

import jakarta.xml.bind.JAXBException;

@Service
public class CreditorDebitRequestImpl implements CreditorDebitService{

@Autowired
private AccountRepo accountRepo;

@Autowired
private ServiceRequestRepo service_requestRepo;

@Autowired
private CreditDebitRequestRepo creditDebitRequestRepo;

@Autowired
private CoreService coreService;

@Autowired
private RabbitTemplate rabbitTemplate;

@Override
public Object getAllCreditordebit_request() {
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	List<Creditordebit_request> creditordebit =creditDebitRequestRepo.findAll();
	for(Creditordebit_request CD:creditordebit) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("Credit_debit_request_Id", CD.getCredit_debit_request_id());
	map.put("Card_Number", CD.getCard_number());
	map.put("Card_Type", CD.getCard_type());
	map.put("Response_Status", CD.getResponse_status());
	map.put("Response_Message", CD.getResponse_message());
	map.put("Service_request_id", CD.getRequest().getService_request_id());
	map.put("Process_id", CD.getProcess_id());
	map.put("Request_date", CD.getRequest_date());
	map.put("Request_message", CD.getRequest_message());
	map.put("Response_date", CD.getResponse_date());
	

	list.add(map);
	}
	return list;
	}
	
@Override
public Object getbyid(int cDid) {
	List<Map<String, Object>>list=new ArrayList<>();

	   List<Account>account=accountRepo.findBycustomerId(cDid);

	   if(!ObjectUtils.isEmpty(account))

	   {

	    for(Account  e:account)

	    {

	    Map<String,Object>map=new HashMap<>();

	    map.put("accountnumber", e.getAccount_number());

	  map.put("customerId",e.getCustomer().getCustomer_id());


	return map;
	}
	   }
	return list;
	}

//@Override
//public Object saveCreditordebit(CreditdebitDto creditordebitdto) {
//	Map<String, Object> map = new HashMap<String, Object>();
//	if(creditordebitdto.getCard_type().isEmpty()||creditordebitdto.getCard_type()==null)
//	{
//	map.put("status","error");
//
//	map.put("msg","enter the valid cardtype");
//	}
//	else if(creditordebitdto.getAccount_number()==0)
//	{
//	map.put("status","error");
//	map.put("msg","enter the valid accountnumber");
//	}
//	else if(creditordebitdto.getService_request_id()==0)
//	{
//	map.put("status","error");
//	map.put("msg","enter the valid requestid");
//	}
//	else if(creditordebitdto.getRequest_message().isEmpty()||creditordebitdto.getRequest_message()==null)
//	{
//	map.put("status","error");
//	map.put("msg","enter the valid requestMessage");
//	}
//	else
//	{
//	Creditordebit_request creditDebitCard= new Creditordebit_request();
//
//	creditDebitCard.setCard_type(creditordebitdto.getCard_type());
//
//	Account account =  accountRepo.findById(creditordebitdto.getAccount_number()).orElse(null);
//
//	     
//
//	creditDebitCard.setAccount(account);
//
//	creditDebitCard.setRequest_date(new Date());
//
//	Service_request serviceRequest =  service_requestRepo.findById(creditordebitdto.getService_request_id()).orElse(null);
//
//	creditDebitCard.setRequest(serviceRequest);
//
//	creditDebitCard.setResponse_status("pending");
//
//	creditDebitCard.setRequest_message(creditordebitdto.getRequest_message());
//
//	Creditordebit_request crdDebCard=creditDebitRequestRepo.save(creditDebitCard);
//
//	map.put("status","success");
//
//	map.put("msg","data saved successfully");
//
//	}
//	return map;
//
//	}
//}

@Override
public Object saverequest(CreditdebitDto creditrequest) throws JAXBException, InstantiationException, IllegalAccessException {

Map<String,Object>map=new HashMap<String, Object>();

if(creditrequest.getCard_type().isEmpty()||creditrequest.getCard_type()==null)
{
map.put("status","error");
map.put("msg","enter the valid cardtype");
}
else if(creditrequest.getAccount_number()==0)
{
map.put("status","error");
map.put("msg","enter the valid accountnumber");
}
else if(creditrequest.getService_request_id()==0)
{
map.put("status","error");
map.put("msg","enter the valid requestid");
}
else
{
Creditordebit_request creditDebitCard= new Creditordebit_request();
creditDebitCard.setCard_type(creditrequest.getCard_type());
Account myAccount =  accountRepo.findById(creditrequest.getAccount_number()).orElse(null);

creditDebitCard.setAccount(myAccount);
creditDebitCard.setRequest_date(new Date());
Service_request serviceRequest =  service_requestRepo.findById(creditrequest.getService_request_id()).orElse(null);
creditDebitCard.setRequest(serviceRequest);
creditDebitCard.setResponse_status("Pending");
creditDebitCard.setRequest_message(creditrequest.getRequest_message());
Creditordebit_request crdDebCard =creditDebitRequestRepo.save(creditDebitCard);
map.put("status","success");
map.put("msg","data saved successfully");

String ReqTemplate = RequestTemplate.RequestXMLTemplate;
com.primesoftinc.message.customerRegister.ServiceRequest serRequest = (com.primesoftinc.message.customerRegister.ServiceRequest) coreService.unmarshal(ReqTemplate, com.primesoftinc.message.customerRegister.ServiceRequest.class);
serRequest.setCustomerId(myAccount.getCustomer().getCustomer_id());
serRequest.setAccountNumber(myAccount.getAccount_number());
serRequest.getRequestType().setRequestDate(new Date());
serRequest.getRequestType().setRequestId(crdDebCard.getCredit_debit_request_id());
serRequest.getRequestType().setRequestMessage(creditrequest.getRequest_message());
serRequest.getRequestType().setTypeOfRequest(2);
serRequest.getRequestType().setRequestData(creditrequest.getCard_type());
String xml = coreService.marshal(com.primesoftinc.message.customerRegister.ServiceRequest.class, serRequest);
System.out.println("/////////" + xml);
rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEYSENDER,xml);
}
return map ;
}


}

