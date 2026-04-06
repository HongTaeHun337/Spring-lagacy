package com.test.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.mybatis.model.AddressDto;
import com.test.mybatis.model.MyBatisDao;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyBatisController {
	
	
	private final MyBatisDao dao;
	
	//반환값(X), 인자값(X) > 정적 쿼리
		@GetMapping(value ="/m1.do")
		public String m1(Model model) {
			
			dao.m1();
			
			return "result";
		}
		
		@GetMapping(value = "/m2.do")
		public String m2(Model model, @RequestParam(name= "seq", defaultValue ="1") String seq) {

			//m2.do?seq=1
			
			int result = dao.m2(seq);
			
			model.addAttribute("result",result);

			return "result";
		}
	
		//반환값(X), 인자값(O)
		//- HashMap
		//- m3
		
		//반환값(X), 인자값(O)
		//- DTO
		//- m4
		
		//반환값(O)
		//- 1행 1열
		//- 원자값 반환(숫자, 문자열 등)
		//- m5
		
		@GetMapping(value = "/m5.do")
		public String m5(Model model, String seq) {

			String name = dao.m5(seq);
			
			model.addAttribute("name", name);

			return "result";
		}
		
		//반환값(O)
		//- 레코드 1개 반환(여러개 컬럼)
		//- m6
		
		
		//반환값(O)
		//- 레코드 N개 반환(1개 컬럼)
		//- m7
		
		
		//반환값(O)
		//- 레코드 N개 + 컬럼 N개
		//- m8
		@GetMapping(value = "/ex08.do")
		public String ex08(Model model) {

			List<AddressDto> list = dao.m8();
			
			model.addAttribute("list",list);

			return "list";
		}
		
}
