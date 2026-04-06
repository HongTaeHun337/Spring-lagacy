package com.test.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.java.model.DIDAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Component
@Service
//@NoArgsConstructor // 기본 생성자
//@AllArgsConstructor //모든 멤버 변수를 인수로 가지는 생성자
@RequiredArgsConstructor // 반드시 초기화가 필요한 생서자

public class DIService {
	
	/*
	
	 	Service > (의존) > DAO
	 	
	 	스프링 의존 주입하는 방법
	 	
	 	1. @Autowired + 생성자 주입 도구
	 	- Spring 2.5
	 	- 가장 많이 사용 + 추천
	
	*/
	
//	private DIDAO dao;
//	
//	@Autowired
//	public DIService(DIDAO dao) {
//		this.dao = dao;
//	}

	
	/*
	 	
	 	2. @Autowired + Setter
	 	
	 	생성자 주입 vs 세터 주입 > 의존 주입
	 	- 생성자: 1회만 호출 가능
	 	- 세터: N회 호출 가능
	 	
	*/
//	private DIDAO dao;
//	
//	@Autowired
//	public void setDao(DIDAO dao) {
//		this.dao = dao;
//	}
	
	
	/*
		3. 멤버 변수에 직접 @Autowired 선언
		- 의존 주입 도구(생성자, Setter)가 필요 없다.
		- 가장 손쉬운 방법
		- 단위 테스트와 유지보수가 어렵다.
	*/
	
//	@Autowired
//	private DIDAO dao;
	
	/*
	 	4. 생성자 주입 + @Autowired 생략
	 	- Spring 4.3 부터
	 	- 생성자가 딱 1개일떄만 지원
	*/
	
//	private DIDAO dao;
//	
//	@Autowired
//	public DIService (DIDAO dao) {
//		this.dao = dao;
//	}
//	
//	public DIService (DIDAO dao, int num) {
//		this.dao = dao;
//	}
	
	/*
	 	5. Setter + Lombok
	 	- 2번 방식과 동일
	 	- Lombok에게 Setter를 만들게 한다.
	*/
//	@Setter(onMethod_ = @Autowired)
//	private DIDAO dao;
	
	
	/*
	 	6. 생성자 + Lombok
	 	- Lombok > 생성자를 어떤 방식으로 만들어 주는가?  
	*/
	private final DIDAO dao;
	private int num;
	private String name;
	
//	public String get() {
//		
////		final int a =10;
////		//a=20;
////		
////		final int b;
////		b= 20;
//	}
	
	//1,6 - 주력
	//3 - 가끔
	
	
	
	public String get() {
		
		//DIDAO dao = new DIDAO(); > 나중에 다른 dao로 교체할 가능성 있다? > 없음
		//MemberDAO dao2 = new MemberDAO();
		
		//DIDAO dao = new MemberDAO();
		
		
		String data = dao.get();
		
		return data;
	}

}
