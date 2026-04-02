package com.test.java.di01;

public class Main {

	public static void main(String[] args) {
		
		//Main.java
		
		//Main(사장) > Service(팀장) > Hong,Lee,Choi(팀원)
		
		//Main > (위임) > Service > (위임) > Hong
		//Main > (의존) > Service > (의존) > Hong
		
		//의존 관계
		//- Main은 Service를 의존한다.
		//- Service를 Mian의 '의존 객체(의존성, Dependency)' 라고 부른다.
		
		// 현재의 객체 생성 순서
		//1. Main
		//2. Service
		//3. Hong
		
		
		System.out.println("Main 자신의 업무");
		
		//Service 입장 > 현재 공간 == 외부 공간
		//Hong hong = new Hong();
		Lee lee = new Lee();
		
		
		
		Service service = new Service(lee);//의존 주입(DI)
		service.doSomething();
		
		System.out.println("Main 자신의 업무");
		
	}
	
}
