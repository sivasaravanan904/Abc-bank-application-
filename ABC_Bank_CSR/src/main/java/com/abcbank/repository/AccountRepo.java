package com.abcbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Account;
import com.abcbank.model.Account_statement;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

	@Query(value="Select * from account where customer_id=:customerId",nativeQuery=true)
	List<Account> findByCustomerId(int customerId);

	@Query(value="Select * from account where customer_id=:customerId",nativeQuery=true)
	List<Account> findBycustomerId(int customerId);

	@Query(value="Select a.* from account a, credit_debitrequest b, lost_stolencard c, cheque_book_request d " + "where b.account_number=a.account_number and c.account_number=d.account_number and a.account_number=:accountNumber",nativeQuery=true)
	List<Account_statement> getbyaccountnumber(@Param("accountNumber") long accountNumber);

	@Query(value="Select * from account where account_number=:account_number",nativeQuery=true)
	List<Account> findbyaccountnumber(Long account_number);

	
}
