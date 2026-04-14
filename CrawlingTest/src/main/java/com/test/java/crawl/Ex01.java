package com.test.java.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Ex01 {
	
	public static void main(String[] args) {

		//Ex01.java
		
		//Jsoup
		//- 브라우저처럼 사이트 접속 > 접속한 페이지 소스 읽어오기(문자열) > 소스 분석 > 탐색 도구 제공 > 원하는 데이터 수집
		
		//https://jsoup.org 주소
		//1. 브라우저 요청
		//2. 서버 > (소스 반환) > 브라우저
		//3. 소스 분석 > 실행 > 화면 출력
		
		//https://jsoup.org 주소
		//1. Jsoup 요청
		//2 서버 > (소스 반환) > Jsoup
		//3. 소스 분석 > 탐색 도구 제공
		
		try {
			
			String url = "https://jsoup.org";
			
			//접속해서 읽어온 문서 내용을 담고 있는 객체
			//- JavaScript의 document 객체와 유사
			Document doc = Jsoup.connect(url).get();
			
			System.out.println(doc.html());
			System.out.println();
			System.out.println();
			System.out.println();
			
			//document.querySelector("CSS 선택자")
			Elements result = doc.select("#jsoup-java-html-parser");
			
			System.out.println(result.size()); //1
			System.out.println(result.get(0).text()); //jsoup: Java HTML Parser
			
			
		} catch (Exception e) {
			System.out.println("Ex01.main");
			e.printStackTrace();
		}

		

	}

}


























