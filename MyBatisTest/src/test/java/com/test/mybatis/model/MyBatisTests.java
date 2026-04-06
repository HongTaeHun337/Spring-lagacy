package com.test.mybatis.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUnit + Spring
//- JUnit4: 생성자 주입 테스트 불가능 > 필드 주입
//- JUnit5: 생성자 주입 테스트 가능
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MyBatisTests {

	@Autowired
	private DataSource dataSource;
	
	//Junit 실행 방법(= 테스트 방법)
	//1. Ctrl + F11
	//	- 파일 내의 모든 @Test 메서드 실행
	//2. @Ignore
	//	- 일부 테스트 무시
	//3. 메서드 헤더 > run as > JUnit test
	//	- 원하는 특정 테스트 실행
	
	
	//JUnit > 테스트용 메서드
	@Test
	public void test() {
		System.out.println("테스트 메서드");
	}
	
	@Test
	public void test2() {
		System.out.println("테스트 메서드2");
	}
	
	@Ignore
	@Test
	public void test3() {
		fail("테스트 실패");
	}
	
	
	
	@Test
	public void testCreateDataSource() {
		
		//비권장
		//System.out.println(dataSource != null);
		
		//평가 메서드 > 권장
		assertNotNull(dataSource);
		
	}
	
	@Test
	public void testCreateConnection() {
		
		Connection conn = null;
		
		try {
			
			conn = dataSource.getConnection();
			
		} catch (Exception e) {
			System.out.println("MyBatisTests.testCreateConnection");
			e.printStackTrace();
		}

		assertNotNull(conn);
		
	}
	
	@Test
	public void testIsConnection() {
		
		Connection conn = null;
		
		try {
			
			conn = dataSource.getConnection();

			assertFalse(conn.isClosed());
			
		} catch (Exception e) {
			System.out.println("MyBatisTests.testCreateConnection");
			e.printStackTrace();
		}		
	}
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Test
	public void testCreateSqlSessionTemplate() {
		assertNotNull(template);
	}
	
	
	@Test
	public void testExecuteQuery() {
		
		//stat.excuteQuery("select..")
		//template.insert(null)
		//template.update(null)
		//template.delete(null)
		//template.select(null)
		
		String time = template.selectOne("address.time");
		
		assertNotNull(time);
		
		System.out.println(time);
		
		
	}
	
}























