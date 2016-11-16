package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Customer;


/* JapRepository 인터페이스에는 findOne, save, findAll, delete 메서드가 기본으로 정의되어 있다. */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	List<Customer> findAllOrderByName();
	
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	Page<Customer> findAllOrderByName(Pageable pageable);	// JPA 가 제공하는 페이징 객체 사용위해 반환값과 매개변수 지정.
}
