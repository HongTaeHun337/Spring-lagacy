package com.test.mybatis.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MyBatisDaoTest {

	@Autowired
	private MyBatisDao dao;
	
	@Test
	public void testCreateDao() {
		assertNotNull(dao);
	}
	
	@Test
	public void testM2() {
		
		int result = dao.m2("1");
		
		assertEquals(1,result);
	}
	
	@Test
	public void testM3() {
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("seq", "1");
		map.put("name", "홍길순");
		map.put("age", "30");
		map.put("address", "부산시");
		map.put("gender", "f");
		
		int result = dao.m3(map);
		
		assertEquals(1, result);
	}
	
	@Test
	public void testM4() {
		
		AddressDto dto = new AddressDto();
		
		dto.setSeq("1");
		dto.setName("홍철수");
		dto.setAge("6");
		dto.setAddress("서울시 강남구 역삼동");
		dto.setGender("m");
		
		int result = dao.m4(dto);
		
		assertEquals(1, result);
		
	}
	
	
	@Test
	public void testM5() {
		
		String name = dao.m5("1");
		
		assertEquals("홍철수", name);
		
	}
	
	@Test
	public void testM6() {
		
		AddressDto dto = dao.m6("1");
		
		assertNotNull(dto);
		
		assertEquals("홍철수", dto.getName());
		
		System.out.println(dto);
		
	}
	
	@Test
	public void testM7() {
		
		List<String> list = dao.m7();
		
		assertEquals(6, list.size());
		
		System.out.println(list);
	}
	
	@Test
	public void testM8() {
		
		List<AddressDto> list = dao.m8();
		
		assertEquals(6, list.size());
		
		System.out.println(list);
		
	}
	
	@Test
	public void testM10() {
		
		List<AddressDto> list = dao.m10(10);
		
		assertEquals(5, list.size());
		
	}
	
	@Test
	public void testM11() {
		
		List<AddressDto> list = dao.m11("강남");
		
		assertEquals(3, list.size());
	}
	
	@Test
	public void testM12() {
		
		List<AddressDto> list = dao.m12("all");
		
		assertEquals(6, list.size());
	}
	
	@Test
	public void testM13() {
		
		AddressDto dto = new AddressDto();
		dto.setAge("10");
		dto.setGender("m");
		
		List<AddressDto> list = dao.m13(dto);
		
		//assertEquals(6, list.size());
		System.out.println(list);
	}
	
	@Test
	public void testM14() {
		
		List<String> buseo = Arrays.asList("영업부", "총무부", "개발부");
		
		List<InsaDto> list = dao.m14(buseo);
		
		System.out.println(list);
		
	}
	
	@Test
	public void testM16() {
		
		List<AddressPointDto> list = dao.m16();
		
		System.out.println(list);
		
	}
	
	
	
}
