package com.test.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value = "/ex05.do")
public class Ex05Controller {

//	@RequestMapping(value= "/ex05.do", method = RequestMethod.GET)
//	public String ex05() {
//		
//		return "ex05";
//	}
//	
//	@RequestMapping(value= "/ex05get.do", method = RequestMethod.GET)
//	public String ex05get() {
//		
//		return "ex05get";
//	}
//	
//	@RequestMapping(value= "/ex05post.do" ,method = RequestMethod.POST)
//	public String ex05post() {
//		
//		return "ex05post";
//	}
	
	//@RequestMapping(value= "/ex05.do", method = RequestMethod.GET)
	@GetMapping(value = "/ex05.do")
	public String ex05() {
		
		return "ex05";
	}
	
	//@RequestMapping(value= "/ex05get.do", method = RequestMethod.GET)
	@GetMapping(value = "/ex05get.do")
	public String ex05get() {
		
		return "ex05get";
	}
	
	//@RequestMapping(value= "/ex05post.do" ,method = RequestMethod.POST)
	@PostMapping(value = "/ex05post.do")
	public String ex05post() {
		
		return "ex05post";
	}
	
	
	

	
	
}
