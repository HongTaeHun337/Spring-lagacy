package com.test.file.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileController {
	
	private final ServletContext servletContext; //application 객체

	@GetMapping(value = "/ex01.do")
	public String ex01(Model model) {
		
		return "ex01";
	}
	
	@PostMapping(value = "/ex01ok.do")
	public String ex01ok(Model model
						, @RequestParam("txt") String txt
						, MultipartFile attach
						) {
		
		System.out.println("txt: " + txt);
		
		System.out.println(attach.getName()); //<input type="file" name="attach"
		System.out.println(attach.getOriginalFilename()); //back.jpg
		System.out.println(attach.getContentType()); //image/jpeg, MIME
		System.out.println(attach.getSize()); //733063B
		System.out.println(attach.isEmpty()); //false > true
		
		//업로드된 파일이 어디 있는지?
		//- 임시 폴더에 업로드 파일 > (이동) > 보관 폴더
		
		String path = servletContext.getRealPath("/resources/files");
		System.out.println(path);
		
		try {
			
			//*** 파일명 중복 방지
			//1. 숫자 붙이기(= cos.jar)
			//2. 고유 파일명 만들기
			//	- 시간_파일명
			//	- 난수_파일명
			//3. UUID
			//	- 시간 + 난수
			
			
//			String filename = getUniqueFileName(path, attach.getOriginalFilename());
//			String filename = getUniqueFileName2(attach.getOriginalFilename());
			String filename = getUniqueFileName3(attach.getOriginalFilename());
			
			File file = new File(path + "\\" + filename);
			attach.transferTo(file); //renameTo
			
			
			
			model.addAttribute("txt", txt);
			model.addAttribute("filename", filename);
			model.addAttribute("filetype", attach.getContentType());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "ex01ok";
	}

	private String getUniqueFileName3(String filename) {
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		
		//4eb09834-384f-40db-b10a-3805faa3e51f
		
		return uuid + "_" + filename;
	}

	private String getUniqueFileName2(String filename) {
		
		//회원.txt > aaaaa_회원.txt
		
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		
		return System.nanoTime() + "_" + filename;
	}

	private String getUniqueFileName(String path, String filename) {
		
		//중복 파일을 체크 > 뒤에 숫자 붙이기
		//- 회원.txt > 회원_1.txt > 회원_2.txt
		int n = 1;
		
		String orgFilename = filename;
		
		File file = null;
		
		while (true) {
			file = new File(path + "\\" + filename);
			
			if (file.exists()) {
				//중복O
				//- 회원.txt > 회원_1.txt
				int lastIndex = orgFilename.lastIndexOf(".");
				
				//회원
				String fileNameWithoutExtension = orgFilename.substring(0, lastIndex);
				
				//.txt
				String extension = orgFilename.substring(lastIndex);
				
				//"회원" + "_" + n + ".txt"
				//> 회원_1.txt
				filename = fileNameWithoutExtension + "_" + n + extension;
				n++;
				
			} else {
				//중복X > 유일한 파일명
				return file.getName(); 
			}
		}//while
		
	}
	
	
	@GetMapping(value = "/download.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String filename, HttpServletRequest req) {

		String path = req.getRealPath("/resources/files");
		Resource resource = new FileSystemResource(path + "\\" + filename);

		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();

		// remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {

			String downloadName = null;

			if (userAgent.contains("Trident")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			} else if (userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}


			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/ex02.do")
	public String ex02(Model model) {

		return "ex02";
	}
	
	@PostMapping(value = "/ex02ok.do")
	public String ex02ok(Model model
						, @RequestParam("txt") String txt
						, MultipartFile[] attach) {
		
		String path = servletContext.getRealPath("/resources/files");
		System.out.println(path);
		
		for (MultipartFile file : attach) {
			
			try {
				
				String filename = getUniqueFileName3(file.getOriginalFilename());
				file.transferTo(new File(path + "\\" + filename));
				
			} catch (Exception e) {
				System.out.println("FileController.ex02ok");
				e.printStackTrace();
			}

		}
		
		model.addAttribute("txt", txt);
		model.addAttribute("attach", attach);
		
		return "ex02ok";
	}
	
	@GetMapping(value = "/ex03.do")
	public String ex03(Model model) {

		return "ex03";
	}
	
	@PostMapping(value = "/ex03ok.do")
	public String ex03ok(Model model
						, @RequestParam("txt") String txt
						, MultipartFile[] attach) {
		
		String path = servletContext.getRealPath("/resources/files");
		System.out.println(path);
		
		for (MultipartFile file : attach) {
			
			try {
				
				String filename = getUniqueFileName3(file.getOriginalFilename());
				file.transferTo(new File(path + "\\" + filename));
				
			} catch (Exception e) {
				System.out.println("FileController.ex03ok");
				e.printStackTrace();
			}

		}
		
		model.addAttribute("txt", txt);
		model.addAttribute("attach", attach);
		
		return "ex03ok";
	}
	
	
}













