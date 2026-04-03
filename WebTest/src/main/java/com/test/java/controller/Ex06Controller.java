package com.test.java.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.java.model.AddressDTO;
import com.test.java.model.SpringDAO;

@Controller
public class Ex06Controller {

	@GetMapping(value = "/ex06.do")
	public String ex06() {
		
		//게시판 > add.do 역할
		
		return "ex06";
	}
	
	/*
	
		클라이언트 페이지(HTML, CSS, JavaScript 코드)
	  	- /java/ex06ok.do
	  	
	  	서버 페이지(자바, JSP 코드)
	  	- /ex06ok.do
	  
	*/
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(HttpServletRequest req) {
//		
//		//인코딩 필터
//		//1. EncodingFilter.java 생성 > 스프링 인코딩 필터 제공
//		//2. web.xml > 필터 등록
//		
////		try {
////			req.setCharacterEncoding("UTF-8");
////		} catch (UnsupportedEncodingException e) {
////			e.printStackTrace();
////		}
//		
//		String data = req.getParameter("data");
//		
//		req.setAttribute("data", data);
//		
//		return "ex06ok";
//	}
//
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(@RequestParam("data") String data, Model model) {
//		
//		//기존 방식
//		//- req.getParameter("key")
//		
//		//스프링 방식
//		//- @RequestParam("key")
//		//- 파라미터 자동 수집 기능
//		
//		model.addAttribute("data", data);
//		
//		
//		return "ex06ok";
//	}
	
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(@RequestParam("data") String data, Model model) {
//
//		//data > 숫자 입력
//		int num = Integer.parseInt(data);
//		
//		model.addAttribute("data", num * 2);
//		
//		return "ex06ok";
//	}
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(@RequestParam("data") int data, Model model) {
//
//		model.addAttribute("data", data * 2);
//		
//		return "ex06ok";
//	}
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(@RequestParam(name = "data", defaultValue = "초깃값") String data, Model model) {
//
//		//- list.do
//		//- list.do?page=1
////		if (data == null || data.equals("")) {
////			data = "기본값";
////		}              == defaultValue 랑 똑같은 역할
//		
//		//defaultValue
//		//- 파라미터 null or "" > 대신할 값
//		
//		
//		model.addAttribute("data", data);
//		
//		return "ex06ok";
//	}
	
	
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(Model model, @RequestParam("name") String name
//			, @RequestParam("age") Integer age, @RequestParam("address") String address) {
//	
//		AddressDTO dto = new AddressDTO();
//		
//		dto.setName(name);
//		dto.setAge(age);
//		dto.setAddress(address);
//		
//		SpringDAO dao = new SpringDAO();
//		
//		dao.add(dto);
//		
//		return "ex06ok";
//	}
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(Model model, AddressDTO dto,
//			@RequestParam("id") String id) {
//
//		//1. @RequestParam("name") > 데이터 가져오기
//		//dto.
//		
//		System.out.println(id);
//		
//		SpringDAO dao = new SpringDAO();
//		
//		dao.add(dto);
//		
//		return "ex06ok";
//	}
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok(
//				//@RequestParam("cb") String[] cb
//				//String[] cb
//				//@RequestParam("cb") List<String> cb
//				//@RequestParam("cb") ArrayList<String> cb
//				//@RequestParam("cb") LinkedList<String> cb
////			------------------------------------------------------- 아래는 안됨			
//				//List<String> cb
//				//AraayList<String> cb
//			) {
//		
//		//System.out.println(Arrays.toString(cb));
//		System.out.println(cb);
//		
//		return "ex06ok";
//	}
	
	@PostMapping(value = "/ex06ok.do")
	public String ex06ok(Model model, 
			@ModelAttribute("data") String data) {
	
		//@ModelAttribute = @RequestParam + model.addAttribute
		
		//model.addAttribute("data", data);
		
		return "ex06ok";
	}
	
	
//	@PostMapping(value = "/ex06ok.do")
//	public String ex06ok() {
//		
//		return "ex06ok";
//	}
	
	
	
	
}






