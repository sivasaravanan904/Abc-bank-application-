package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.service.AccountStatementService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AccountStatementController {
	
	@Autowired
	private AccountStatementService statementService;

	 @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    private String extractTokenFromHeader(HttpServletRequest request) {
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7); // Remove "Bearer " prefix
	        }
	        return null;
	    }

	
	@GetMapping("/getstatement/{accountNumber}")
	public Object getStatement(HttpServletRequest request,@PathVariable Long accountNumber)
	{
		  try {
	            String token = extractTokenFromHeader(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	                return new ResponseEntity<>(statementService.getstatement(accountNumber), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	        }
	}
}
