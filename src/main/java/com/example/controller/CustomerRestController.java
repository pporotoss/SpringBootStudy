package com.example.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Customer> getCustomers(@PageableDefault(page=0, size=10) Pageable pageable) {
		Page<Customer> customers = customerService.findAll(pageable);
		return customers;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
		Customer created = customerService.create(customer);
		
		URI location = uriBuilder.path("api/customers/{id}")
											.buildAndExpand(created.getId()) // path() 메서드에 있는 플레이스홀더의 값을 설정.
											.toUri();	// path() 메서드에 지정한 경로로 URI 생성.
		
		HttpHeaders headers = new HttpHeaders();	// Http 응답 헤더 생성.
		headers.setLocation(location);	// Http 응답 헤더에 location 속성을 설정.
		
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		return customerService.update(customer);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
	
}
