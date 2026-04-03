package com.test.java.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		//Mian.java
		/*
		
		  	AOP, Aspect Oriented Programming
		  	- 관점 지향 프로그래밍
		  	- 주업무 + 보조업무 > 같은 파일에 작성하는 경우가 흔하다.
		  				 	> 코드 관리 불편, 가독성 저하, 중복 코드 발생
		  
		*/
		
		//m1();
		//m2();
		//m3();
		m4();
		
		
	}//main

	
	private static void m4() {
		
		ApplicationContext context 
		= new ClassPathXmlApplicationContext("com/test/java/aop/memo.xml");
	
		Memo memo = (Memo)context.getBean("memo");
		
		//memo.addMemo("메모를 작성합니다,");
		//memo.del("1");
		
		//boolean result = memo.del("1");
		
		try {
			System.out.println(memo.readMemo("4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	private static void m3() {
		
		//우리가 직접 만든 객체 > 스프링의 관리를 못받는다

		//Memo memo = new MemoImpl();
		
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("com/test/java/aop/memo.xml");
		
		Memo memo = (Memo)context.getBean("memo");
		
		memo.addMemo("메모입니다.");
		try {
			String content = memo.readMemo("1");
			System.out.println("안녕하세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(memo.edit("1", "수정합니다."));
		
		System.out.println(memo.del("1"));
		
	}

	private static void m2() {
		
		
		Memo memo = new MemoImpl();
		
		memo.addMemo("메모입니다.");
		try {
			String content = memo.readMemo("1");
			System.out.println("안녕하세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(memo.edit("1", "수정합니다."));
		
		System.out.println(memo.del("1"));	
		
	}

	private static void m1() {
		
		//순수 자바
		Memo memo = new MemoImpl();
		
		memo.addMemo("메모입니다.");
		try {
			String content = memo.readMemo("1");
			System.out.println("안녕하세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(memo.edit("1", "수정합니다."));
		
		System.out.println(memo.del("1"));
		
		
		
	}
	
}
