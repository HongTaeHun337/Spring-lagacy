package com.test.mybatis.model;

import java.util.HashMap;
import java.util.List;

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
	
}











