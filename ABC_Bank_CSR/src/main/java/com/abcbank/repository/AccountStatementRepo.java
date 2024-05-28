package com.abcbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Account_statement;

@Repository
public interface AccountStatementRepo extends JpaRepository <Account_statement, Integer> {
	
	//Select * from account_statement  where account_number="";
//	@Query(value = "Select * from Account_statement where account_number=:account_number", nativeQuery = true)
	
	@Query(value="Select * from account_statement  where account_number=:accountNumber",nativeQuery =true)
	List< Account_statement> findByAccountnumber(@Param("accountNumber") Long accountNumber);

}
