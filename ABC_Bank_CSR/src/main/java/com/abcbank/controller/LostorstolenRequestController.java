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

import com.abcbank.dto.LostorStolenDto;
import com.abcbank.model.Lostorstolen_request;
import com.abcbank.service.LostorstolenRequestService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
@CrossOrigin

@RestController
public class LostorstolenRequestController {
	
	@Autowired
    private LostorstolenRequestService lostorstolen_requestService;
	
	 @Autowired
	 private JwtTokenUtil jwtTokenUtil;
	 
	
	    private String extractTokenFromHeader(HttpServletRequest request) {
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7); // Remove "Bearer " prefix
	        }
	        return null;
	    }
	 

     @GetMapping("/fetchAcc/{customer_id}")
       public Object getbyCustomerId(HttpServletRequest request,@PathVariable int customer_id) {
    	 try {
	            String token = extractTokenFromHeader(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	                return new ResponseEntity<>(lostorstolen_requestService.getbycustomerid(customer_id), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
       }
     }

//       @GetMapping("/getbyservicerequestId/{service_request_id}")
//       public Object getbyserviceId(@PathVariable int service_request_id) {
//           return (lostorstolen_requestService.getbyservicerequestid(service_request_id));
//       }
//
       @PostMapping("/savelost")

        public Object savelost(HttpServletRequest request,
        @RequestBody LostorStolenDto dtos)
        throws JAXBException, InstantiationException, IllegalAccessException{
        
                return new ResponseEntity<>(lostorstolen_requestService.savelostcard(dtos), HttpStatus.OK);
            
	}

}
