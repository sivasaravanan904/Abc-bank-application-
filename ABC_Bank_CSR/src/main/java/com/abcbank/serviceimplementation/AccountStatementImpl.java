package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.abcbank.model.Account_statement;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.AccountStatementRepo;
import com.abcbank.service.AccountStatementService;

@Service
public class AccountStatementImpl implements AccountStatementService{
	
	
	
	@Autowired
	private AccountStatementRepo accountStatementRepo;

	@Override
	public Object getstatement(Long accountNumber) {
		
		List<Map <String,Object>>list=new ArrayList<Map<String,Object>>();
		List<Account_statement>statements=accountStatementRepo.findByAccountnumber(accountNumber);

		if(!ObjectUtils.isEmpty(statements)) {
		for(Account_statement b:statements)
		{ Map<String,Object>map=new HashMap<>();
		map.put("date", b.getDate());
		map.put("Description",b.getDescription());
		map.put("creditamount",b.getCredit_amount());
		map.put("Debitamount",b.getDebit_amount());
		map.put("ChequeNo",b.getCheque_ref_no());
		map.put("closingBalance",b.getClosing_balance());
		map.put("accountNumber",b.getAccount().getAccount_number());
		list.add(map);
		
		}

		}
		else
		{
		Map<String,Object>map=new HashMap<>();
		map.put("status","error");
		map.put("msg","Invalid account number");
		return map;
		}

		return list;
		
	}
	}

	
	
	



