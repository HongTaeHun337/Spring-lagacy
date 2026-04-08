package com.test.api.naver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ex02 {
	
	public static void main(String[] args) {

		//Ex02.java
		
		//JSON Parser
		//- String(JSON 형식) > 파싱(구문 분석) > 자바 데이터
		//- json-simple
		
		String data = "{\r\n"
				+ "    \"name\": \"홍길동\",\r\n"
				+ "    \"age\": 20,\r\n"
				+ "    \"address\": {\r\n"
				+ "        \"sido\": \"서울시\",\r\n"
				+ "        \"gugun\": \"강남구\"\r\n"
				+ "    },\r\n"
				+ "    \"nick\": [ \"강아지\", \"고양이\", \"병아리\" ]\r\n"
				+ "}";
				
		
		JSONParser parser = new JSONParser();
		
		 try {
			
			 JSONObject obj = (JSONObject)parser.parse(data);
			 
			 System.out.println(obj.get("name"));
			 System.out.println(obj.get("age"));
			 System.out.println(obj.get("address"));
			 System.out.println(obj.get("nick"));
			 
			 JSONObject address = (JSONObject)obj.get("address");
			 System.out.println(address.get("sido"));
			 
			 JSONArray nick = (JSONArray)obj.get("nick");
			 System.out.println(nick.get(1));
			 
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		

	}

}








