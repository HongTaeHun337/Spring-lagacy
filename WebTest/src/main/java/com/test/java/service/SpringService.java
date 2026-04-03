package com.test.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.java.model.SpringDAO;

/*

 	스프링을 통한 의존 주입
 	- 객체 > 스프링 빈
 	
 	스프링 빈을 만드는 방법
 	1. XML
 		- <bean class="">
 		
 	2. 어노테이션
 		- @Component	> 스프링 빈
 		- @Controller	> 스프링 빈 + 역할 부여 > 컨트롤러 역할
 		- @Service		> 스프링 빈 + 역할 부여 > 서비스 역할
 		- @Repository	> 스프링 빈 + 역할 부여 > DAO 역할
 	
*/

//@Component
@Service
public class SpringService {

	private SpringDAO dao;
	
	@Autowired //의존 주입해라!!
	public SpringService(SpringDAO dao) {
		this.dao = dao;
	}
	
	
	public String doSomething() {
		
		SpringDAO dao = new SpringDAO();
		
		String data = dao.work();
		
		return data;
	}
	
}
