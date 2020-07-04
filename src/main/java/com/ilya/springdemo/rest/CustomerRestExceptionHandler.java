package com.ilya.springdemo.rest;

import javax.xml.ws.soap.Addressing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	//add exc handler for customer not found
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponce> handleException(CustomerNotFoundException exc){
		
		//create customer  error responce object
		CustomerErrorResponce error = new CustomerErrorResponce(
					HttpStatus.NOT_FOUND.value(),
					exc.getMessage(),
					System.currentTimeMillis()
				);
		//return respince etity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
    //add another exc handler for any exc
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponce> handleException(Exception exc){
		
		//create customer  error responce object
		CustomerErrorResponce error = new CustomerErrorResponce(
					HttpStatus.BAD_REQUEST.value(),
					exc.getMessage(),
					System.currentTimeMillis()
				);
		//return respince etity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
