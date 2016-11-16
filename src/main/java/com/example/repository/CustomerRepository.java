package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Customer;

@Repository
@Transactional
public class CustomerRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return  new Customer(id, firstName, lastName);
	};
	
	public List<Customer> findAll() {
		String sql = "SELECT id, first_name, last_name FROM customers ORDER BY id";
		List<Customer> customers = jdbcTemplate.query(sql, customerRowMapper);
		return customers;
	}
	
	public Customer findOne(Integer customerId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		return jdbcTemplate.queryForObject(sql, param, customerRowMapper);
	}
	
	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		String sql = "UPDATE customers SET first_name = :firstName, last_name = :lastName";
		if(customer.getId() == null) {
			sql = "INSERT INTO customers(first_name, last_name) values(:firstName, :lastName)";
		}
		jdbcTemplate.update(sql, param);
		return customer;
	}
	
	public void delete(Integer customerId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		String sql = "DELETE FROM customers WHERE id = :id";
		jdbcTemplate.update(sql, param);
	}
}
