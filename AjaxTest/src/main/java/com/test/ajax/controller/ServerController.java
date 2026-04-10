package com.test.ajax.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.ajax.model.AddressDto;
import com.test.ajax.model.AjaxDao;
import com.test.ajax.model.SeqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ServerController {
	
	private final AjaxDao dao;
	
	@GetMapping(value = "/address")
	public List<AddressDto> list() {
		
		return dao.list();
	}
	
	@GetMapping(value = "/address/more")
	public List<AddressDto> more(Integer index) {
		
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
		
		return dao.more(index);
	}
	
	@PostMapping(value = "/address")
	public int add(@RequestBody AddressDto dto) {
		
		return dao.add(dto);
	}
	
	@DeleteMapping(value = "/address/{seq}")
	public int del(@PathVariable("seq") String seq) {
		
		return dao.del(seq);
	}
	
	@DeleteMapping(value = "/address")
	public int delAll(@RequestBody SeqDto dto) {
		
		//System.out.println(dto);
		
		int n = 0;
		
		for (String seq: dto.getSlist()) {
			n += dao.del(seq);
		}
	
		
		return n == dto.getSlist().length ? 1 : 0;
	}

}








