package com.cav.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cav.dto.Customer;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Service
public class CustomerService {

	@Autowired
	private ObservationRegistry registery;
	
	private List <Customer> customerList = new ArrayList<Customer>();
	
	public Customer addCustomer(Customer customer) {
		customerList.add(customer);
		return Observation.createNotStarted("addCustomer", registery).observe(()->customer);
		
	}
	
	public List <Customer>  getCustomers(){
		return Observation.createNotStarted("getCustomers", registery).observe(()->customerList);
	}
	
	public Customer getCustomer(int id) {
		return Observation.createNotStarted("getCustomers", registery)
				.observe(()->customerList.stream().filter(cust -> cust.id()==id).
				       findAny().orElseThrow(() -> new RuntimeException("No Customerfound with key: " + id)));
	}


		
}
