package com.test.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClientController {
	
	@GetMapping(value = "/ex08.do")
	public String ex08(Model model) {

		return "ex08";
	}
	
	
	
}










