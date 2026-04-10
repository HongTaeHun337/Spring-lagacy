package com.test.ajax.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AjaxDao {

	private final SqlSessionTemplate template;

	public int countAddress() {
		
		return template.selectOne("ajax.countAddress");
	}

	public String getName(String seq) {
		
		return template.selectOne("ajax.getName", seq);
	}

	public int checkId(String id) {
		
		return template.selectOne("ajax.checkId", id);
	}

	public List<UserDto> listUser() {
		
		return template.selectList("ajax.listUser");
	}

	public List<AddressDto> list() {
		return template.selectList("ajax.list");
	}

	public int add(AddressDto dto) {
		
		return template.insert("ajax.add", dto);
	}

	public int del(String seq) {
		
		return template.delete("ajax.del", seq);
	}

	public List<AddressDto> more(Integer index) {
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("begin", index);
		map.put("end", index + 4);
		
		return template.selectList("ajax.more", map);
	}
	
}









