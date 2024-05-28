package com.abcbank.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.model.Account;
import com.abcbank.service.AccountService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AccountController {
	
	@Autowired
	private AccountService myAccountService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String extractTokenFromHeader(HttpServletRequest request) {
		// Extract token from the Authorization header or any other header where you
		// pass the token
		// For example, if you pass the token in the Authorization header as "Bearer
		// <token>"
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7); // Remove "Bearer " prefix
		}
		return null;
	}

	@GetMapping("/accdetails/{customerId}")
	public Object getaccountDetails(HttpServletRequest request,@PathVariable int customerId)
	{
		try {
			
			String token = extractTokenFromHeader(request);
			String tokenCheck = jwtTokenUtil.validateToken(token);

			if (tokenCheck.equalsIgnoreCase("valid")) {
				return new ResponseEntity<>(myAccountService.getaccountdetails(customerId), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	}
	}
}
