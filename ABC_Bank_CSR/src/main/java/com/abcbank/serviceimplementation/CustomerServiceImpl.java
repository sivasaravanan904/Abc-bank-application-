package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.dto.CustomerDto;
import com.abcbank.model.Customer;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
    private CustomerRepo customerRepo;
	
	
	public Object getAllCustomer() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Customer> customers = customerRepo.findAll();
		
		for(Customer c:customers) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Customer Id", c.getCustomer_id());
			map.put("Customer Name", c.getName());
			map.put("Customer UserName", c.getUserName());
			map.put("Customer Password", c.getPassword());
			map.put("Customer Dob", c.getDob());
			map.put("Customer phone_nO", c.getPhone_no());
			map.put("Customer Door_Street", c.getDoor_street());
			map.put("Customer City", c.getCity());
			map.put("Customer Pincode", c.getPincode());
			map.put("Customer Pan_No", c.getPan_no());
			map.put("Customer Email", c.getEmail());
			map.put("Customer State", c.getState());
			list.add(map);
		}
		return list;
	}


//	public Object updateCustomer(Customer customer) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(customer.getName().isEmpty()) {
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Customer Name :");
//		}
//		else if(customer.getUserName().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The  UserName :");
//		}
//		else if(customer.getPassword().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Password :");
//		}
//		else if(customer.getDob() == null){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please enter the DOB :");
//		}
//		else if(customer.getPhone_no().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Phone_No :");
//		}
//		else if(customer.getDoor_street().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Door_street :");
//		}
//		else if(customer.getCity().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The City :");
//		}
//		else if(customer.getPincode().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Pincode :");
//		}
//		else if(customer.getPan_no().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Pan_no :");
//		}
//		else if(customer.getEmail().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The Email :");
//		}
//		else if(customer.getState().isEmpty()){
//			map.put("Status", "Error!!!");
//			map.put("Msg :", "Please Enter The State :");
//		}
//		else {
//			customerRepo.save(customer);
//			map.put("Status", "Success");
//			map.put("Msg", "Data Update successfully");
//		}
//		return map;
//		
//	}

	@Override
	public Object validateUser(Customer customer) {
		Map<String, Object> map = new HashMap<String, Object>();
        String userName = customer.getUserName();
        String password = customer.getPassword();


            Customer login = customerRepo.find(userName);

            if(login != null) {
                if(login.getPassword().equals(password)) {
                    map.put("Status", "Success");
                    map.put("Message", "Login Successful");
                    map.put("customerId",login.getCustomer_id());
                    map.put("name",login.getName());
                }
                else {
                    map.put("Status", "Error!!!");
                    map.put("Message", "Invalid Password");
                }
            }
            else {
                map.put("Status", "Erro!!!");
                map.put("Message", "Invalid Username");
            }
        return map;
    }




	@Override
	public Object updateById(CustomerDto customerDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		Customer customer = customerRepo.findById(customerDto.getCustomer_id()).orElse(null);
		if(customer!=null) {
			customer.setCustomer_id(customerDto.getCustomer_id());
			customer.setDoor_street(customerDto.getDoor_street());
			customer.setCity(customerDto.getCity());
			customer.setPincode(customerDto.getPincode());
			customer.setPhone_no(customerDto.getPhone_no());
			customer.setEmail(customerDto.getEmail());
			
			customerRepo.saveAndFlush(customer);
			map.put("Status", "Succuess");
			map.put("Message", "Customer Data Updated Successfully");
		}else {
			map.put("Status", "Error!!!");
			map.put("Message", "Invalid Customer ID");
		}
		return map;
	}


	@Override
	public Object getCustomer(int customer_id) {
		
	
		
		Map<String, Object> map = new HashMap<>();
		Customer customer = customerRepo.findById(customer_id).orElse(null);
		if (customer != null)

		{

			map.put("Name", customer.getName());
			map.put("username", customer.getUserName());
			map.put("dob", customer.getDob());
			map.put("Email", customer.getEmail());
			map.put("phoneNo", customer.getPhone_no());
			map.put("door_street", customer.getDoor_street());
			map.put("panNo", customer.getPan_no());
			map.put("city", customer.getCity());
			map.put("state", customer.getState());
			map.put("pincode", customer.getPincode());
			map.put("password", customer.getPassword());

	
		} else {
			map.put("Status", "Error");
			map.put("Msg", "Invalid customerId");
		}
		return map;

	}


	@Override
	public Object savecustomer(Customer customer) {
		
		return null;
	}


	
}
