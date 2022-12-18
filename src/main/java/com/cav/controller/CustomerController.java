package com.cav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.annotation.security.RolesAllowed;

import com.cav.dto.Customer;
import com.cav.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	 @GetMapping("/statusAdmin")
	 @RolesAllowed("ROLE_ADMIN")
	 public String getStatusAdmin() {
		 return "Welcome to admin page";
	 }
	
	 @GetMapping("/statusClient")
	 @RolesAllowed({"ROLE_ADMIN", "ROLE_CLIENT"})
	 public String statusClient() {
		 return "Welcome to client page";
	 }
	 
	 @RolesAllowed({"ROLE_ADMIN", "ROLE_SUPPORT"})
	 @GetMapping("/statusSupport")
	 public String statusSupport() {
		 return "Welcome to support page";
	 }

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		service.addCustomer(customer);
		return customer;
	}
	
	@GetMapping
	public List <Customer>  getCustomers(){
		return service.getCustomers();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return service.getCustomer(id);
	}

}
