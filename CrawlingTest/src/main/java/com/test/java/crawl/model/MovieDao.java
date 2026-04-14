package com.test.java.crawl.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDao {

	public List<MovieDto> list() {
		
		List<MovieDto> mlist = new ArrayList<MovieDto>();

		try {

			String url = "https://www.moviechart.co.kr/rank/boxoffice";

			Document doc = Jsoup.connect(url).get();

			Elements list = doc.select(".listTable > table > tbody > tr");

			for (Element tr : list) {

				MovieDto dto = new MovieDto();
				
				String title = tr.select(".title > a").text();
				String seq = tr.select(".redAc").text();
				String date = tr.select(".date").text();
				
				dto.setSeq(seq);
				dto.setTitle(title);
				dto.setDate(date);
				

				Document subdoc = Jsoup.connect("https://www.moviechart.co.kr" + tr.select(".title > a").attr("href")).get();

				String genre = subdoc.select("#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(2) > dl > dt").text();
				String director = subdoc.select("#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(3) > dl > dt").text();
				String actor = subdoc.select("#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(4) > dl > dt").text();
				String[] actorList = actor.split(",");
				String poster = subdoc.select("#content > div.info > div > div.movieIner > div.poster > a > img").attr("src");
				
				dto.setGenre(genre);
				dto.setDirector(director);
				dto.setActor(actorList);
				dto.setPoster(poster);
				
				mlist.add(dto);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mlist;

	}

}





