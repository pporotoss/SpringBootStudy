package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import com.example.service.LoginUserDetails;

@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute	// 클래스 안의 모든 메서드의 모델객체에 자동으로 매핑된다. 기본 이름은 반환하는 클래스명. 기본이름 : 메서드명 X
	private CustomerForm setUpForm() {
		return new CustomerForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Validated CustomerForm form, BindingResult result, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		// @AuthenticationPrincipal을 매개변수 앞에 붙여주면, 현재 로그인 상태에 있는 UserDetails 인터페이스를 구현한 LoginUserDetails 객체를 가져올 수 있다.
		
		if(result.hasErrors()) {
			return list(model);
		}
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer, userDetails.getUser());
		
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)	// 요청으로 넘어오는 파라미터중에 이름이 form이 있으면 매핑. 
	public String editForm(@RequestParam Integer id, CustomerForm form) {
		Customer customer = customerService.findOne(id);
		BeanUtils.copyProperties(customer, form);
		
		return "customers/edit";
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result, @AuthenticationPrincipal LoginUserDetails userDetails) {
		if(result.hasErrors()) {
			return editForm(id, form);
		}
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.update(customer, userDetails.getUser());
				
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "edit", params = "goToTop")
	public String goToTop() {
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
	
}
