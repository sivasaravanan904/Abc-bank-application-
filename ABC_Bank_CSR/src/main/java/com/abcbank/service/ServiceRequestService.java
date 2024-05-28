package com.abcbank.service;

import com.abcbank.dto.ServicerequestDto;

public interface ServiceRequestService {

	public Object getbyserviceid(ServicerequestDto dto);

	public Object getaccountalldetails(int customerId);

}
