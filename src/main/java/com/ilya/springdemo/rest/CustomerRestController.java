package com.ilya.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilya.springdemo.entity.Customer;
import com.ilya.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowire the customer service
	@Autowired
	private CustomerService customerService;
	
	//add mapping for /GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	//add mapping for get customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer id not found - "+customerId);
		}
		
		return customer;
	}
	
	//add mapping for post /customers - add new user
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {		
		customer.setId(0);		
		customerService.saveCustomer(customer);
		return customer;
	}
	
	//add mapping for pi-ut /customers - update new user
	@PutMapping("/customers")
	public Customer upfateCustomer(@RequestBody Customer customer) {				
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer id not found - "+customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Customer "+customerId+" deleted";
	}
}
