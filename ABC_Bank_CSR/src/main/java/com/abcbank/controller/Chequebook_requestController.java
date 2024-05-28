package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.Chequebook_requestDto;
import com.abcbank.model.Chequebook_request;
import com.abcbank.service.Chequebook_requestService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;

@CrossOrigin
@RestController
public class Chequebook_requestController {

	@Autowired
	private Chequebook_requestService chequebookService;
	
	 @Autowired
	 private JwtTokenUtil jwtTokenUtil;
	
		
//	@PostMapping("/saveCheque")
//	public Object saveCheque(@RequestBody Chequebook_request chequebookRequest) {
//		return (chequebookService.saveCheque(chequebookRequest));
//	}
	 private String extractTokenFromHeader(HttpServletRequest request) {
	        String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7); // Remove "Bearer " prefix
	        }
	        return null;
	    }
	@GetMapping("/getCard")
	public Object getCard(HttpServletRequest request) {
		 try {
	            String token = extractTokenFromHeader(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	                return new ResponseEntity<>(chequebookService.getAllCards(), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	        }
    }
	


//	@GetMapping("/getbyid/{service_request_id}/{chequebook_request_id}")
//	public Object getcardlById(HttpServletRequest request,
//			@PathVariable int service_request_id,
//			@PathVariable int chequebook_request_id) {
//		 try {
//	            String token = extractTokenFromHeader(request);
//	            String tokenCheck = jwtTokenUtil.validateToken(token);
//
//	            if (tokenCheck.equalsIgnoreCase("valid")) {
//	                return new ResponseEntity<>(chequebookService.getcardById(service_request_id, chequebook_request_id), HttpStatus.OK);
//	            } else {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
//	            }
//	        } catch (Exception e) {
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
//	        }
//    }
	
	@GetMapping("/getbyid/{id}")
//	@GetMapping("/getbyid/Chequebook/{id}")
    public Object getcardlById(HttpServletRequest request,
    		@PathVariable int id) {
		 try {
	            String token = extractTokenFromHeader(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	                return new ResponseEntity<>(chequebookService.getcardById(id), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	        }
    }
	
	 @PostMapping("/postcardrequest")
	    public Object saveCardrequest(HttpServletRequest request,@RequestBody Chequebook_requestDto chequeBookDTO ) 
	    		throws JAXBException, InstantiationException, IllegalAccessException {
		 try {
	            String token = extractTokenFromHeader(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	            	return new ResponseEntity<>(chequebookService.saverequest(chequeBookDTO), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	        }
	 }
}
	 
	
//	@PutMapping("/updateCheque")
//	public Object updateById(HttpServletRequest request, @RequestBody Chequebook_requestDto chequeBookRequest)
//            throws JAXBException, InstantiationException, IllegalAccessException {
//		 try {
//	            String token = extractTokenFromHeader(request);
//	            String tokenCheck = jwtTokenUtil.validateToken(token);
//	            if (tokenCheck.equalsIgnoreCase("valid")) {
//	                return new ResponseEntity<>(chequebookService.updateById(chequeBookRequest), HttpStatus.OK);
//	            } else {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
//	            }
//		 } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
//		 }
//	}

