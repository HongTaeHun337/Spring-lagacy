package com.test.java.crawl;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex03 {
	
	public static void main(String[] args) {

		//Ex03.java
		/*
		
			JSoup
			- 정적 콘텐츠 크롤링 도구
			- HTML 소스상에 있는 내용을 탐색
			- JavaScript 결과는 탐색 불가능(***) > CSR의 방식은 크롤링 불가능
			
			Selenium, Playwright
			- 크롤링 도구(X)
			- 자동화 테스트 도구(O) > 사람 대신 브라우징을 하는 프로그램
			
			1. 드라이버 설치(의존성 추가)
			2. 크롬 드라이버 설치(chromedriver.exe)
			
		*/
		//m1();
		//m2();
		//m3();
		m4();

	}

	private static void m4() {
		
		String url = "http://localhost:8080/ajax/ex08.do";
		
		String webDriverID = "webdriver.chrome.driver";
		String path = "C:\\dev\\chromedriver.exe";
		System.setProperty(webDriverID, path);
		
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		
		List<WebElement> list = driver.findElements(By.cssSelector("#tbl1 tbody tr"));
		
		for (WebElement tr : list) {
			
			String name = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
			System.out.println(name);
			
		}
		
		
		
	}
	
	
	

	private static void m3() {
		
		String name = "홍길동";
		String pw = "전화번호";
		
		String url = "http://lms1.sist.co.kr/worknet/SLogin.asp";
		
		String webDriverID = "webdriver.chrome.driver";
		String path = "C:\\dev\\chromedriver.exe";
		System.setProperty(webDriverID, path);
		
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		driver.findElement(By.id("strLoginID")).sendKeys(name);
		driver.findElement(By.id("strLoginPwd")).sendKeys(pw);
		
		driver.findElement(By.cssSelector(".login-btn > input")).click();
		
		
		//** 페이지 전환 > 잠시 쉬었다 가기
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//1:1 문의 버튼 클릭
		driver.findElement(By.cssSelector("#content div.panel-body > div.popbtmbtn_section > div > a:nth-child(9)")).click();
		
		
		//** 모달창 > 잠시 쉬었다 가기
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//글쓰기 버튼 클릭
		driver.findElement(By.id("saveBt")).click();
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//작성
		driver.findElement(By.cssSelector("input[name=formBD_SUBJECT]")).sendKeys("질의 사항이 있습니다.");
		
		
	}

	private static void m2() {
		
		String url = "https://naver.com";
		
		String webDriverID = "webdriver.chrome.driver";
		String path = "C:\\dev\\chromedriver.exe";
		System.setProperty(webDriverID, path);
		
		//= 자바(사람)가 사용하는 브라우저
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		WebElement e1 = driver.findElement(By.id("query"));
		e1.sendKeys("스프링 부트");
		
		WebElement e2 = driver.findElement(By.className("btn_search"));
		e2.click();
		
	}

	private static void m1() {
		
		String url = "http://localhost:8080/ajax/ex08.do";
		
		try {
			
			Document doc = Jsoup.connect(url).get();
			
			Elements list = doc.select("#tbl1 tbody tr");
			
			System.out.println(list.size());
			
		} catch (Exception e) {
			System.out.println("Ex03.main");
			e.printStackTrace();
		}
		
	}

}
