package com.test.mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.mybatis.model.AddressDto;
import com.test.mybatis.model.InsaDto;
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
		
		
		@GetMapping(value = "/m9.do")
		public String m9(Model model, @RequestParam("column") String column, @RequestParam("word") String word) {

			//검색
			//- /m9.do?column=name&word=강아지
			//- /m9.do?column=gender&word=m

			//SQL
			//- select * from tblAddress where name = '강아지'
			//- select * from tblAddress where gender = 'm'
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("column", column);
			map.put("word", word);
			
			List<AddressDto> list = dao.m9(map);
			
			model.addAttribute("list",list);
			
			return "list";				

		}
		
		@GetMapping(value = "/m15.do")
		public String m15(Model model) {

			//이후 예제 > MyBatis 수업 == JPA 수업
			
			//1. insert + tblAddress
			//2. insert + tblPoint + 1000p 누적
			
			//1. 
			AddressDto dto = new AddressDto();
			dto.setName("상어");
			dto.setAge("10");
			dto.setGender("m");
			dto.setAddress("동해");
			
			dao.add(dto);
			
			//2. 방금 가입 + 1000p
			dao.addPoint();

			return "result";
		}
		
		
		@GetMapping(value = "/m17.do")
		public String m17(Model model) {

			/*
				DB
				- 테이블간의 관계 > PK-FK
				
				Java(OOP)
				- 클래스(객체)의 관계
				
				
			*/
			
			//Join
			//- tblAddress + tblPoint
			
			List<AddressDto> alist = dao.m17();
			
			model.addAttribute("alist",alist);

			return "list";
		}
		
		
		@GetMapping(value = "/m18.do")
		public String m18(Model model) {

			//Join
			//- tblInsa(1) : tblProject(N)
			
			List<InsaDto> ilist = dao.m18();
			
			model.addAttribute("ilist",ilist);

			return "list";
		}
		
}









