package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.dto.ServicerequestDto;
import com.abcbank.model.Account;
import com.abcbank.model.Chequebook_request;
import com.abcbank.model.Creditordebit_request;
import com.abcbank.model.Lostorstolen_request;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.Chequebook_requestRepo;
import com.abcbank.repository.CreditDebitRequestRepo;
import com.abcbank.repository.LostorstolenRequestRepo;
import com.abcbank.repository.ServiceRequestRepo;
import com.abcbank.service.ServiceRequestService;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService{

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CreditDebitRequestRepo cardRequestRepo;
	@Autowired
	private Chequebook_requestRepo bookRequestRepo;
	@Autowired
	private LostorstolenRequestRepo cardRepo;
	

	
	@Override
	public Object getbyserviceid(ServicerequestDto dto) {
		
		List<Map<String,Object>>list=new ArrayList<>();

		if(dto.getService_request_id()==1)
		{
		System.out.println("//////@@@@@@@@@"+dto.getService_request_id());

		List<Chequebook_request>bookRequest=bookRequestRepo.getrequeststatus(dto.getAccount_number());

		for(Chequebook_request chequeBookRequest:bookRequest)
		{
		Map<String,Object>map=new HashMap<>();
		map.put("requestdate", chequeBookRequest.getRequest_date());
		map.put("responseDate", chequeBookRequest.getResponse_date());
		map.put("responseMessage", chequeBookRequest.getResponse_message());
		map.put("responsestatus",chequeBookRequest.getResponse_status());
		map.put("serviceid",chequeBookRequest.getRequest().getService_request_id());
//		map.put("requesttype",chequeBookRequest.getRequest().getRequest_type());	
		list.add(map);
		}


		}
else if(dto.getService_request_id()==2)
		{
		System.out.println("//////@@@@@@@@@"+dto.getAccount_number());
		List<Creditordebit_request>credits=cardRequestRepo.findbyaccountnumber(dto.getAccount_number());
		for(Creditordebit_request credit:credits)
		{
		Map<String,Object>map=new HashMap<>();
		map.put("requestdate", credit.getRequest_date());
		map.put("responseDate", credit.getResponse_date());
		map.put("responseMessage", credit.getResponse_message());
		map.put("responsestatus",credit.getResponse_status());
		map.put("serviceid",credit.getRequest().getService_request_id());
//		map.put("requesType",credit.getRequest().getRequest_type());
		list.add(map);
		System.out.println("LIST"+list);
		}

		}
		else if(dto.getService_request_id()==3)
		{
		List<Lostorstolen_request>lost=cardRepo.findbyaccountnumber(dto.getAccount_number());
		for(Lostorstolen_request LostStolenCard:lost)
		{
		Map<String,Object>map=new HashMap<>();
		map.put("responseDate", LostStolenCard.getResponse_date());
		map.put("requestdate", LostStolenCard.getRequest_date());
		map.put("responseMessage", LostStolenCard.getResponse_message());
		map.put("responsestatus",LostStolenCard.getResponse_status());
		map.put("serviceid",LostStolenCard.getRequest().getService_request_id());
//		map.put("requestType",LostStolenCard.getRequest().getRequest_type());
		list.add(map);
		}
		}
		else
		{
		List<Account>lost=accountRepo.findbyaccountnumber(dto.getAccount_number());
		Map<String,Object>map=new HashMap<>();
		for(Account card:lost)
		{
			List<Map<String,Object>> chequeList= new ArrayList<>();
		       List<Map<String,Object>> cardDebList= new ArrayList<>();
		       List<Map<String,Object>>  lostenCardList= new ArrayList<>();
		     
		List<Chequebook_request> chequebook = card.getChequebook_requests();
		       for(Chequebook_request cheque : chequebook)
		       {
		       
		        Map<String,Object>chequeMap=new HashMap<>();
		        chequeMap.put("requestDate", cheque.getRequest_date());
		        chequeMap.put("responseDate", cheque.getResponse_date());
		        chequeMap.put("responseMessage", cheque.getResponse_message());
		        chequeMap.put("responsestatus",cheque.getResponse_status());
		        chequeMap.put("serviceid", cheque.getRequest().getService_request_id());
//		        chequeMap.put("requestType",cheque.getRequest().getRequest_type());

		        chequeList.add(chequeMap);
		      map.put("chequebook", chequeList);
//		         list.add(map);
		       
		       }
		               
		       List<Creditordebit_request> creditCard = card.getCreditordebit_requests();
		       for(Creditordebit_request cardDeb : creditCard)
		       {
		        Map<String,Object>cardDebMap=new HashMap<>();
		        cardDebMap.put("requestDate", cardDeb.getRequest_date());
		        cardDebMap.put("responseDate", cardDeb.getResponse_date());
		        cardDebMap.put("responseMessage", cardDeb.getResponse_message());
		        cardDebMap.put("responsestatus",cardDeb.getResponse_status());
		        cardDebMap.put("serviceid", cardDeb.getRequest().getService_request_id());
//		        cardDebMap.put("requestType",cardDeb.getRequest().getRequest_type());
		        cardDebList.add(cardDebMap);
		        map.put("cardDebitCard",cardDebList);
//		         list.add(map);
		       }
		       List<Lostorstolen_request> lostenCard = card.getLostorstolen_requests();
		       for(Lostorstolen_request losten : lostenCard)
		       {
		        Map<String,Object>lostenCardMap=new HashMap<>();
		        lostenCardMap.put("requestDate", losten.getRequest_date());
		        lostenCardMap.put("responseDate", losten.getResponse_date());
		        lostenCardMap.put("responseMessage", losten.getResponse_message());
		        lostenCardMap.put("responsestatus",losten.getResponse_status());
		        lostenCardMap.put("serviceid", losten.getRequest().getService_request_id());
//		        lostenCardMap.put("requestType",losten.getRequest().getRequest_type());
		        
		        lostenCardList.add(lostenCardMap);
		        map.put("lostenCard",lostenCardList);
//		         list.add(map);
		       }      
		}
		list.add(map);
		}
		return list;

		}




		



	@Override
	public Object getaccountalldetails(int customerId) {
		
		List<Map<String, Object>>list=new ArrayList<>();
        List<Account>myaccount=accountRepo.findBycustomerId(customerId);
        if(myaccount!=null)
        {
            for(Account  e:myaccount)
            {
                Map<String,Object>map=new HashMap<>();
                map.put("accountNumber", e.getAccount_number());
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

}