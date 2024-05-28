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

import com.abcbank.dto.CreditdebitDto;
import com.abcbank.service.CreditorDebitService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
@CrossOrigin
@RestController
public class CreditorDebitRequestController {

	 @Autowired
     private  CreditorDebitService  creditordebit_requestService;
	 
	

//    @PostMapping("/savecreditordebit")
//    public Object savecreditdebit(@RequestBody CreditdebitDto creditdebitDto) {
//        return (creditordebit_requestService.saveCreditordebit(creditdebitDto));
//    }

	 @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    private String extractTokenFromHeader(HttpServletRequest request) {
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7); // Remove "Bearer " prefix
	        }
	        return null;
	    }
	 
	 

    @GetMapping("/getcreditordebit")
    public Object getcreditordebit(HttpServletRequest request){
    	try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return new ResponseEntity<>(creditordebit_requestService.getAllCreditordebit_request(), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }

    @GetMapping("/getbyid/{CDid}")
    public Object getbycustomerid(HttpServletRequest request,
    		@PathVariable int CDid) {
    	try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return new ResponseEntity<>(creditordebit_requestService.getbyid(CDid), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }
    @PostMapping("/savecardrequest")
    public Object saveCardrequest(HttpServletRequest request,@RequestBody CreditdebitDto creditrequest )
    		throws JAXBException, InstantiationException, IllegalAccessException{
    	 try {
             String token = extractTokenFromHeader(request);
             String tokenCheck = jwtTokenUtil.validateToken(token);

             if (tokenCheck.equalsIgnoreCase("valid")) {
                 return new ResponseEntity<>(creditordebit_requestService.saverequest(creditrequest), HttpStatus.OK);
             } else {
                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
             }
         } catch (Exception e) {
    	
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
         }
    }
}
