package com.test.java.aop;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;

//보조 업무 > 로그 기록
public class Logger {
	
	public void log() {
		
		Calendar now = Calendar.getInstance();
		System.out.printf("[%tF %tT] 로그를 기록합니다.\r\n", now, now);
		
	}
	
	public void test() {
		System.out.println("test 보조 업무입니다.");
	}
	
	//특정 업무 > 소요 시간
	public void time(ProceedingJoinPoint jp) {
	
		long begin = System.nanoTime();
		
		//주업무 실행
		//- memo.addMemo(""); X
		//- 프록시 객체 > jp
		try {
			jp.proceed();			
		} catch (Throwable e) {
			e.printStackTrace();
		}

		
		long end = System.nanoTime();
		
		System.out.printf("소요 시간: %,dns\r\n", end - begin);
		
		
	}
	
	public void checkValid(Object result) {
		//System.out.println("보조 업무 실행: " + result);
		
		if ((boolean)result) {
			System.out.println("주업무 정상 동작");
		} else {
			System.out.println("주업무 비정상 동작");
		}
		
	}
	
	public void logException(Exception e) {
		System.out.println("보조 업무 실행" + e.getMessage());
		
		//에러 발생 > 대응
		//- Log 기록
		//- 담당자 연락
		
		
		
	}

}
