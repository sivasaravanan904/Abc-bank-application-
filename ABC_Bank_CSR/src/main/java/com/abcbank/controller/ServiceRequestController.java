package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.ServicerequestDto;
import com.abcbank.service.ServiceRequestService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class ServiceRequestController {

	@Autowired
	private ServiceRequestService requestService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	   private String extractTokenFromHeader(HttpServletRequest request) {
	        // Extract token from the Authorization header or any other header where you pass the token
	        // For example, if you pass the token in the Authorization header as "Bearer <token>"
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7); // Remove "Bearer " prefix
	        }
	        return null;
	    }
	
	@PostMapping("/getbyseviceid")
	public Object getbyserviceid(HttpServletRequest httpRequest,
			@RequestBody ServicerequestDto dto)
	{
		  	try {
		        String token = extractTokenFromHeader(httpRequest);

		        // Validate the token
		        String tokenCheckResult = jwtTokenUtil.validateToken(token);

		        if (tokenCheckResult.equalsIgnoreCase("valid")) {
		            // Token is valid, proceed with your logic
		            return requestService.getbyserviceid(dto);
		        } else {
		            // Token is invalid, return unauthorized response
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheckResult);
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
		    }
	}
		  
		  
	@GetMapping("/accnumbers/{customerId}")
	public Object getaccountDetails(HttpServletRequest request,@PathVariable int customerId)
	{
			try {
			String token = extractTokenFromHeader(request);
			String tokenCheck = jwtTokenUtil.validateToken(token);

			if (tokenCheck.equalsIgnoreCase("valid")) {
				return new ResponseEntity<>(requestService.getaccountalldetails(customerId), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
		}
	}
}
