package com.test.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ex04.do")
public class Ex04Controller {

	//요청 메서드 
	@RequestMapping
	public String ex04() {
		
		return "ex04";
	}
	
}
