package com.test.mybatis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisDao {
	
	@Autowired
	private SqlSessionTemplate template;
	
	/*
	
	 	SqlSessionTemplate > SQL 실행
	 	- SQL 구문은 XML Mapper에 작성
	 	
	 	int stat.executeUpdate()
	 	- 반환값이 없는 쿼리
	 	- int template.insert()
	 	- int template.update()
	 	- int template.delete()
	 	
	 	ResultSet stat.executeQuery()
	 	- 반환값이 있는 쿼리
	 	- template.selectOne()	> 결과셋의 레코드 1개(= if (rs.next()) {})
	 	- template.selectList() > 결과셋의 레코드 N개(= while (rs.next()) {})
	 		
	*/

	public void m1() {
		
		//insert
		//-insert into tblAddress (seq, name, age, address, gender)values (seqAddress.nextval, '돼지',3,'서울시 강남구 대치동','m');
	
		//JDBC
		//1. Connection open
		//2. SQL
		//3. Statement
		//4. executeUpdate()
		
		template.insert("address.m1");
		
	}

	//단위 테스트
	public int m2(String seq) {
		
		return template.update("address.m2", seq);
		
	}
	
	public int m3(HashMap<String, String> map) {
		
		return template.update("address.m3", map);
		
	}
	
	public int m4(AddressDto dto) {
		
		return template.update("address.m4", dto);
	}
	
	public String m5(String seq) {
		
		//JDBC
		//1. Connection
		//2. SQL
		//3. PreparedStatement
		//4. ? 1 > seq
		//5. executeQuery()
		//6. ResultSet
		//7. if (rs.next())
		//8. rs.getString("name")
		//9. return
		
		//매핑
		return template.selectOne("address.m5", seq);
		
	}
	
	
	public AddressDto m6(String seq) {
		
		
		return template.selectOne("address.m6", seq);
	}
	
	public List<String> m7() {
		
		//1. Connection
		//2. SQL
		//3. Statement
		//4. executeQuery()
		//5. ResultSet
		//6. List<String> names
		//7. while (rs.next()) {}
		//8. rs.getString("name")
		//9. names.add(name)
		//10. return names
		
		return template.selectList("address.m7");	
		
	}
	
	public List<AddressDto> m8() {
		
		//1. Connection
		//2. SQL
		//3. Statement
		//4. executeQuery()
		//5. ResultSet
		//6. List<String> names
		//7. while (rs.next()) {}
		//8. rs.getString("name")
		//9. names.add(name)
		//10. return names
		
		return template.selectList("address.m8");
	}

	public List<AddressDto> m9(Map<String, String> map) {
		
		
		//동등 비교
		return template.selectList("address.m9", map);
	}
	
	public List<AddressDto> m10(int age) {
		
		//우위 비교
		//.. where age >= 10
		return template.selectList("address.m10", age);
		
	}
	
	public List<AddressDto> m11(String word) {
		
		//Like 검색
		//.. where address like '%강남%'
		return template.selectList("address.m11", word);
	}
	
	public List<AddressDto> m12(String gender) {
		
		//- m12.do?gender=m
		//- m12.do?gender=f
		//- m12.do?gender=all
		return template.selectList("address.m12", gender);
	}
	
	public List<AddressDto> m13(AddressDto dto) {
		
		//- m13.do?age=20
		//- m13.do?age=20&gender=m
		//- m13.do?gender=m
		//- m13.do?address=강남
		
		//- where age = 20
		//- where gender = 'f'
		//- where address like = '%강남%'
		//- where age =20 and gender = 'f'
		//- where age =20 and address like = '%강남%'
		//- where age =20 and gender = 'f' and address like = '%강남%'
		
		
		return template.selectList("address.m13", dto);
	}
	
	public List<InsaDto> m14(List<String> buseo) {
		
		//mybatis.query("id", 매개변수))
		//- 단일값
		//- Map
		//- Dto
		//- List
		
		//- where buseo = '영업부' or buseo = '총무부' or buseo = '개발부'
		//- where buseo in ('영업부', '총무부', '개발부')
		
		return template.selectList("address.m14", buseo);
	}

	public void add(AddressDto dto) {
		
		template.insert("address.add", dto);		
	}

	public void addPoint() {
	
		template.insert("address.addPoint");
	}
	
	public List<AddressPointDto> m16() {
		
		//조인
		//- tblAddress + tblPoint
		
		return template.selectList("address.m16");
	}

	public List<AddressDto> m17() {
		
		return template.selectList("address.m17");
	}

	public List<InsaDto> m18() {
		
		
		
		return template.selectList("address.m18");
	}
	
	
}











