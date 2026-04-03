package com.test.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 구현
//1. Controller 인터페이스 구현
//2. @Controller 어노테이션 사용

@Controller
@RequestMapping(value = "/ex03.do") //@WebServlet("/ex03.do")
public class Ex03Controller {

	//doGet(), doPost(), handleRequest() > 요청 메서드 부재
	
	//요청 메서드 > 역할 부여
//	@RequestMapping
//	public ModelAndView aaa() {
//		
//		//업무 > JSP 호출
//		ModelAndView mv = new ModelAndView("ex03");
//		return mv;		
//	}
	
//	@RequestMapping
//	public String aaa(HttpServletRequest req) {
//		
//		//데이터 넘길 방법??
//		String name = "강아지";
//		
//		req.setAttribute("name", name);
//				
//		return "ex03";
//	}
//	
//	public void bbb() {
//		
//	}
//	
//	public void ccc() {
//		
//	}
	
	
	@RequestMapping
	public String aaa(Model model) {
		
		//ModelAndView > Model
		
		//데이터 넘길 방법??
		String name = "강아지";
		
		model.addAttribute("name", name);
				
		return "ex03";
	}
	
	public void bbb() {
		
	}
	
	public void ccc() {
		
	}
	
}












