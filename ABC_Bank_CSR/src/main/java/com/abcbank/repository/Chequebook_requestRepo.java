package com.abcbank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Chequebook_request;

@Repository
public interface Chequebook_requestRepo extends JpaRepository<Chequebook_request, Integer> {

	 @Query(value="Select * from cheque_book_request where service_request_id=:service_request_id",nativeQuery=true)
	 List<Chequebook_request> findbyserviceid(int service_request_id);

//	 @Query(value="Select * from cheque_book_request where responsestatus=:accountNumber",nativeQuery=true)
//	 List<Chequebook_request> getrequeststatus(String status);
	 
	 @Query(value="Select * from chequebook_request where account_number=:account_number",nativeQuery=true)
	 List<Chequebook_request> getrequeststatus(Long account_number);

//	@Query(value="Select count(*)from chequebook_request where request_date=:date && account_number=:account_number",nativeQuery = true)
//	Chequebook_request findbydateandaccountNumber(@Param("date")Date date,@Param ("account_number") Long account_number);


}
