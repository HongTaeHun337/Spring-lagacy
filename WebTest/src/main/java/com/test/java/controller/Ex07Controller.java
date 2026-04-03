package com.test.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.java.model.AddressDTO;

@Controller
public class Ex07Controller {

	//요청 메서드의 반환값 종류
	
	//1. String
	//- JSP 파일명
	//- ViewResolver 사용
//	@GetMapping(value = "/ex07.do")
//	public String ex07(Model model) {
//		
//		return "ex07";
//	}

	
	//2. void
	//- Spring 4.3 이후 지원
	//- 요청 주소의 이름과 동일한 JSP를 호출한다.
//	@GetMapping(value = "/ex07.do")
//	public void ex07(Model model) {
//
//		//ex07.jsp를 자동으로 호출한다.
//	}
	
	//3. String > 키워드
	//- redirect: > resp.sendRedirect() 역할
	//- forward:  > pageContext.forward() 역할
//	@GetMapping(value = "/ex07.do")
//	public String ex07(Model model) {
//		
//
//		return "redirect:/ex06.do";
//		//return "forward:/ex06.do";
//	}
	
	//4. RedirectAttributes
//	@GetMapping(value = "/ex07.do")
//	public String ex07(Model model, RedirectAttributes rttr) {
//
//		//업무 처리 > 다른 페이지 이동
//		//- list.do?page=10&search=y
//		
//		String page = "20";
//		String search ="n";
//
//		rttr.addAttribute("page", page);
//		rttr.addAttribute("search", search);
//		
//		//http://localhost:8080/java/list.do?page=10&search=y
//		//return "redirect:/list.do?page=" + page + "&search=" + search;
//		
//		
//		//http://localhost:8080/java/list.do?page=20&search=n
//		return "redirect:/list.do";
//	}
	
	//5. JSON 반환
	//- 의존성 추가
	//- @responseBody
	@GetMapping(value = "/ex07.do")
	public @ResponseBody AddressDTO ex07(Model model) {

		AddressDTO dto = new AddressDTO();
		
		dto.setName("강아지");
		dto.setAge(3);
		dto.setAddress("서울시 강남구 대치동");
		
		/*
		 	
		 	JSON, JavaScript Object Notation
		 	- 자바스크립트 객체 표현

		*/
		
		System.out.println(dto);
		
		return dto;
	}

}























