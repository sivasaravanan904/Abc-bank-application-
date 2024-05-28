package com.abcbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Creditordebit_request;
import com.abcbank.service.CreditorDebitService;

@Repository
public interface CreditDebitRequestRepo extends JpaRepository<Creditordebit_request, Integer> {

//	List<Creditordebit_request> findbyaccountnumber(long accountNumber);
//
//	List<Creditordebit_request> getrequeststatus(String status);
//
//
//	@Query(value="Select * from Creditordebit_request where service_request_id=:service_request_id",nativeQuery=true)
//
//	List<Creditordebit_request> findbyserviceid(int service_request_id);
//
//
//
//	@Query (value="Select *from Creditordebit_request where account_number=:account_number",nativeQuery = true)

//	List<Creditordebit_request> findbyaccountnumber(Long account_number);

	@Query(value="Select * from credit_debitrequest where service_request_id=:service_request_id",nativeQuery=true)
	List<Creditordebit_request> findByservicerequestId(int service_request_id);

//	List<Creditordebit_request> getrequeststatus(String status);
	
	 @Query(value="Select * from creditordebit_request where account_number=:account_number",nativeQuery=true)
	List<Creditordebit_request> findbyaccountnumber(Long account_number);

	
}
