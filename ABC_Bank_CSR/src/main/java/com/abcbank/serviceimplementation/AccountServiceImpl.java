package com.abcbank.serviceimplementation;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.abcbank.model.Account;
import com.abcbank.repository.AccountRepo;
import com.abcbank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public Object getaccountdetails(int customerId) {
		List<Map<String,Object>>	 list=new ArrayList<>();
		List<Account>accounts=accountRepo.findByCustomerId(customerId);
		if(!ObjectUtils.isEmpty(accounts))
		{
		for(Account a:accounts)
		{
		Map<String,Object>map=new HashMap<>();
		map.put("accountNumber", a.getAccount_number());
		map.put("accountType", a.getAccount_type());
		map.put("accountBalance", a.getAccount_balance());
		map.put("branchName", a.getBranch_name());
		map.put("Ifsccode", a.getIfsc_code());
		map.put("Name", a.getCustomer().getName());
		list.add(map);
		}
		}
		else
		{
		Map<String,Object>map=new HashMap<>();
		map.put("status","error");

		map.put("msg", "customerId is invalid");
		return map;
		}
		return list;

		}


}
