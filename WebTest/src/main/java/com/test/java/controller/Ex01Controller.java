package com.test.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*

	스프링
	- 서블릿을 사용 안함
	- 일반 클래스를 사용함
	
	
	컨트롤러
	- 이전의 서블릿 역할
	
*/

//스프링 빈
public class Ex01Controller implements Controller{

	//요청 메서드
	//- doGet(), doPost() == handleRequest()
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//업무 진행..
		//- RequestDispatcher > forward(req, resp) > JSP 호출
		
		//ModelAndView
		//1. Model 역할 > 데이터 전송(처리)
		//2. View 역할 > JSP
		
		int count = 100;
		request.setAttribute("count", count);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("ex01"); //ex01.jsp
		//mv.setViewName("/WEB-INF/views/ex01.jsp");
		
		mv.addObject("count", count);//request.setAttribute("count", count)
		
		//return 발생 > 뷰리졸버 개입 > 접두어 + "ex01" + 접미어
		//"/WEB-INF/views/" + "ex01" + ".jsp"
		
		//- "/WEB-INF/views/ex01.jsp"
		//- "/ex01"
		
		
		return mv;
	}

}
