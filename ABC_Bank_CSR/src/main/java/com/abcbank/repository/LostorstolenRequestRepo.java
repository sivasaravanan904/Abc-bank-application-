package com.abcbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Lostorstolen_request;

@Repository
public interface LostorstolenRequestRepo extends JpaRepository<Lostorstolen_request, Integer> {

	@Query(value="select * from Lostorstolen_request where service_request_id=:service_request_id ",nativeQuery=true)
    List<Lostorstolen_request> findbyservicerequestid(int service_request_id);


    @Query(value="select * from lostorstolen_request where account_number=:account_number",nativeQuery=true)

    List<Lostorstolen_request> findbyaccountnumber(Long account_number);
}
