package com.abcbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.model.Service_request;

public interface ServiceRequestRepo extends JpaRepository<Service_request, Integer> {


}
