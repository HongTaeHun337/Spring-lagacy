package com.test.java.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex02 {
	
	public static void main(String[] args) {

		//Ex02.java
		try {
			
			String url = "https://www.moviechart.co.kr/rank/boxoffice";
			
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc.html());
			
			Elements list = doc.select(".listTable > table > tbody > tr");
			
			//System.out.println(list);
			//document.queryQuery("")
			//어떤 태그.find("CSS")
			
			for (Element tr : list) {
				
				String title = tr.select(".title > a").text();
				System.out.println(title);
				
				System.out.printf("%s - %s, %s\n"
								, tr.select(".redAc").text()
								, tr.select(".date").text()
								, tr.select(".audience").text());
				System.out.println();
				
				//영화 상세 페이지
				//- https://www.moviechart.co.kr/info/movieinfo/detail/20261782
				//- /info/movieinfo/detail/20261782 
				
				//System.out.println(tr.select(".title > a").attr("href"));
				
				Document subdoc = Jsoup.connect("https://www.moviechart.co.kr" + tr.select(".title > a").attr("href")).get();
				
				String genre = subdoc.select("#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(2) > dl > dt").text();
				System.out.println(genre.split("/")[0].trim());
				System.out.println();
				System.out.println();
				System.out.println();
				
			} //for
			
		} catch (Exception e) {
			System.out.println("Ex02.main");
			e.printStackTrace();
		}


	}

}

















