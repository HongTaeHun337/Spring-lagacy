package com.test.ajax.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.ajax.model.AjaxDao;
import com.test.ajax.model.UserDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AjaxController {

	private final AjaxDao dao;
	
	@GetMapping(value = "/ex01.do")
	public String ex01(Model model) {

		return "ex01";
	}
	
	@GetMapping(value = "/ex01ok.do")
	public String ex01ok(Model model) {
		
		int count = dao.countAddress();
		
		model.addAttribute("count", count);
		
		return "ex01"; //브라우저 > (요청) > HTML(JSP) 페이지
	}
	
	@GetMapping(value = "/ex02.do")
	public String ex02(Model model) {

		return "ex02";
	}
	
	//JSON 반환
	@GetMapping(value = "/ex02ok.do")
	public @ResponseBody String ex02ok(Model model) throws Exception {
		
		int count = dao.countAddress();
		
		//model.addAttribute("count", count);
		
		//return "ex02"; //ajax 객체 > (요청) > HTML 페이지
		
		if (count > 0) throw new Exception();
		
		return count + "";
	}
	
	
	@GetMapping(value = "/ex03.do")
	public String ex03(Model model) {

		return "ex03";
	}
	
	@GetMapping(value = "/ex03ok.do")
	public @ResponseBody String ex03ok(Model model) {
		
		int count = dao.countAddress();
		
		//시간 끌기..
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		return count + "";
	}
	
	@GetMapping(value = "/ex04.do")
	public String ex04(Model model) {

		return "ex04";
	}
	
	@PostMapping(value = "/ex04ok.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String ex04ok(Model model, String seq) {
		
		//ORA-17004: 열 유형이 부적합합니다.: 1111
		System.out.println(seq);
		
		String name = dao.getName(seq);
		
		System.out.println(name);
		
		return name;
	}
	
	
	@GetMapping(value = "/ex05.do")
	public String ex05(Model model) {

		return "ex05";
	}
	
	@PostMapping(value = "/ex05ok.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String ex05ok(Model model, String seq) {
				
		String name = dao.getName(seq);
		
		return name;
	}
	
	
	@GetMapping(value = "/ex06.do")
	public String ex06(Model model) {

		return "ex06";
	}
	
	@GetMapping(value = "/ex06ok.do")
	public @ResponseBody String ex06ok(Model model, String id) {
		
		int result = dao.checkId(id);
		
		return result + "";
	}
	
	@GetMapping(value = "/ex07.do")
	public String ex07(Model model) {

		return "ex07";
	}
	
	@GetMapping(value = "/ex07ok.do")
	public @ResponseBody String ex07ok(Model model) {
				
		return "ajax";
	}
	
	@GetMapping(value = "/ex07_2ok.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<UserDto> ex07_2ok(Model model) {
		
		//다중값 반환 > select * from tblUser
		
		List<UserDto> list = dao.listUser();
				
		return list;
	}
	
}










