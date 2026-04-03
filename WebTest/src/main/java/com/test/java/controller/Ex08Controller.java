package com.test.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.java.service.SpringService;

@Controller
public class Ex08Controller {

	@Autowired
	private SpringService service;

	//생성자 주입 + @Autowired
	@Autowired
	public Ex08Controller(SpringService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/ex08.do")
	public String ex08(Model model) {
		
		//Controller > (의존) > Service > (의존) > DAO

		//SpringService service = new SpringService();
		
		String data = service.doSomething();
		
		model.addAttribute("data", data);

		return "ex08";
	}
	
}
