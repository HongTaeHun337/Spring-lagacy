package com.test.java.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class AddMember {
	
	//의존 주입 타입 > 인터페이스 or 부모 클래스 > 자식 2개 이상
	//1. 딱 1개 클래스 만 구현, 나머지는 삭제
	//2. @Qualifier 사용
	
	@Autowired
	/* @Qualifier("bCryptPasswordEncoder") */
	private PasswordEncoder encoder;
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Test
	public void testEncoder() {
		
		assertNotNull(encoder);
		
		String pw = "1111";
		System.out.println(encoder.encode(pw));
		
	}
	
	@Test
	public void add() {
		
		
		MemberDto dto = new MemberDto();
		
		dto.setMemberid("dog");
		dto.setMemberpw(encoder.encode("1111"));
		dto.setMembername("강아지");
		dto.setEmail("dog@gmail.com");
		dto.setGender("m");
		
		assertEquals(1, template.insert("security.add", dto));
		
	}
	
	@Test
	public void add2() {
		
		//회원 추가
		MemberDto dto = new MemberDto();
		
		dto.setMemberid("cat");
		dto.setMemberpw(encoder.encode("1111"));
		dto.setMembername("고양이");
		dto.setEmail("cat@gmail.com");
		dto.setGender("f");
		
		assertEquals(1, template.insert("security.add", dto));
		
	}
	
	@Test
	public void add3() {
		
		//회원 추가
		MemberDto dto = new MemberDto();
		
		dto.setMemberid("tiger");
		dto.setMemberpw(encoder.encode("1111"));
		dto.setMembername("호랑이");
		dto.setEmail("tiger@gmail.com");
		dto.setGender("m");
		
		assertEquals(1, template.insert("security.add", dto));
		
	}
	
	

}
