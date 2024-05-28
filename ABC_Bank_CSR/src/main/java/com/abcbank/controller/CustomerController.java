package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.CreditdebitDto;
import com.abcbank.dto.CustomerDto;
import com.abcbank.model.Customer;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.service.CustomerService;
import com.abcbank.utils.JwtTokenUtil;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;

@CrossOrigin
@RestController
public class CustomerController {

	
    	@Autowired
    	private CustomerService customerservice;
    	
    	@Autowired
    	private CustomerRepo customerRepo;
    	
    	@Autowired
    	private JwtTokenUtil jwtTokenUtil;
  
    	@Autowired
    	private BCryptPasswordEncoder bCryptPasswordEncoder;
    	
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

//       @PostMapping("/validateUser")
//       public Object login(@RequestBody Customer customer) {
//           return customerservice.validateUser(customer);
//       }
       
//       @PostMapping("/validateUser")
//       public  ResponseEntity<Object> validateUser(@RequestBody Customer customer){
//    	   String hashedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
//    	   customer.setPassword(hashedPassword);
//    	   ;
//    	   if (customerRepo.save(customer).getCustomer_id()>0){
//	            return ResponseEntity.ok("User Was Saved");
//	        }
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Not Saved, Internaer Server Error. Please Try Again");
//       }


//       @GetMapping("/getall")
//       public Object getCustomer() {
//           return (customerservice.getAllCustomer());
//       }
//
//       @GetMapping("/getdetails/{customer_id}")
//       public Object getCustomer(HttpServletRequest request,@PathVariable int customer_id) {
//    	   try {
//				
//				String token = extractTokenFromHeader(request);
//				String tokenCheck = jwtTokenUtil.validateToken(token);
//	
//				if (tokenCheck.equalsIgnoreCase("valid")) {
//					return new ResponseEntity<>(customerservice.getCustomer(customer_id), HttpStatus.OK);
//				} else {
//					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
//				}
//			} catch (Exception e) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
//			}
//       }
    	
    	

    	@GetMapping("/getdetails/{customer_id}")
    	public ResponseEntity<?> getCustomer(HttpServletRequest request, @PathVariable int customer_id) {
    	    try {
    	        // Extract the Bearer token from the Authorization header
    	        String authorizationHeader = request.getHeader("Authorization");
    	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    	            String token = authorizationHeader.substring("Bearer ".length());
    	            // Validate the token using the JwtTokenUtil class
    	            String tokenCheck = jwtTokenUtil.validateToken(token);
    	            if (tokenCheck.equalsIgnoreCase("valid")) {
    	                return new ResponseEntity<>(customerservice.getCustomer(customer_id), HttpStatus.OK);
    	            } else {
    	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
    	            }
    	        } else {
    	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to missing or invalid token");
    	        }
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
    	    }
    	}
    	
       @PutMapping("/update")
       public Object updateById(@RequestBody CustomerDto customerDto, HttpServletRequest request) {
    	   try {
				
				String token = extractTokenFromHeader(request);
				String tokenCheck = jwtTokenUtil.validateToken(token);
	
				if (tokenCheck.equalsIgnoreCase("valid")) {
					return new ResponseEntity<>(customerservice.updateById(customerDto), HttpStatus.OK);
	
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
			}
       }
       
       @PostMapping("/customerRegister")
       public ResponseEntity<Object> registerUser(@RequestBody Customer customer){
           String hashedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
           customer.setPassword(hashedPassword);
           
           if (customerRepo.save(customer).getCustomer_id()>0){
               return ResponseEntity.ok("User Was Saved");
           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Not Saved, Internal Server Error. Please Try Again");
       }
       
       
       @PostMapping("/customersave")
       public Object saveCustomer(HttpServletRequest request,@RequestBody Customer customer )
       		throws JAXBException, InstantiationException, IllegalAccessException{
       	 try {
                String token = extractTokenFromHeader(request);
                String tokenCheck = jwtTokenUtil.validateToken(token);

                if (tokenCheck.equalsIgnoreCase("valid")) {
                    return new ResponseEntity<>(customerservice.savecustomer(customer), HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
                }
            } catch (Exception e) {
       	
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
            }
       }
       
       

       @PostMapping("/generate-token")
       public ResponseEntity<Object> generateToken(@RequestBody CustomerDto customerDto) {
           Customer databaseUser = customerRepo.find(customerDto.getUserName());

           if (databaseUser == null) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, User Does Not Exist");
           }
           // Assuming the password is stored as plain text in the database
        	if(new BCryptPasswordEncoder().matches(customerDto.getPassword(),databaseUser.getPassword())) {   
        	   String token = jwtTokenUtil.generateToken(customerDto.getUserName());
               customerDto.setToken(token);
               customerDto.setExpirationTime("60 Sec");
               customerDto.setCustomer_id(databaseUser.getCustomer_id());
               customerDto.setMessage("Login Successful");
               return ResponseEntity.ok(customerDto);
           } else {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password Doesn't Match. Verify");
           }
       }
        
       
//       @Operation(summary = "Generate authentication token")
//       @PostMapping("/generate-token")
//       public ResponseEntity<Object> generateToken(@RequestBody CustomerDto customerDto) {
//           Customer databaseUser = customerRepo.find(customerDto.getUserName());
//
//           if (databaseUser == null) {
//               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, User Does Not Exist");
//           }
//           // Assuming the password is stored as plain text in the database
//           if (customerDto.getPassword().equals(databaseUser.getPassword())) {
//               String token = jwtTokenUtil.generateToken(customerDto.getUserName());
//               customerDto.setToken(token);
//               customerDto.setExpirationTime("60 Sec");
//               customerDto.setCustomer_id(databaseUser.getCustomer_id());
//               customerDto.setMessage("Login Successful");
//               return ResponseEntity.ok(customerDto);
//           } else {
//               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password Doesn't Match. Verify");
//           }
//       }


       
       @PostMapping("/validate-token")
       public ResponseEntity<Object> validateToken(@RequestBody CustomerDto customerDto){
           return ResponseEntity.ok(jwtTokenUtil.validateToken(customerDto.getToken()));
       }       
}