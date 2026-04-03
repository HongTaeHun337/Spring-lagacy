package com.test.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ex04post.do")
public class Ex04PostController {
	
		@RequestMapping
		public String ex04post() {
			
			return "ex04post";
		}
		
	}