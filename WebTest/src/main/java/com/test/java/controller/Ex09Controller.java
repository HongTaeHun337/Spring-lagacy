package com.test.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.java.service.DIService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class Ex09Controller {
	
	private final DIService service;
	
	
//	@Autowired
//	public Ex09Controller(DIService service) {
//		this.service = service;
//	}

	//요청 메서드 역할
	//@RequestMapping(value = "/ex09.do", method = RequestMethod.GET)
	@GetMapping(value= "/ex09.do")
	public String ex09(Model model) {
		
		//컨트롤러 > (의존) > 서비스 > (의존) > DAO
		
		//DIService service = new DIService();
		
		String data = service.get();
		
		model.addAttribute("data", 123);
		
		return "ex09";
	}
	
}
