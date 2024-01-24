package com.matzalal.web.controller.api;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.User;
import com.matzalal.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Slf4j
@RestController("apiUserController")
@RequestMapping("/api/users")
public class UserController {

	@Value("${custom.path.upload-images}")
	private String fileDir;

	@Autowired
	private UserService service;

	@GetMapping("edit")
	public boolean edit(
			@RequestParam(name = "q") String query) {
		System.out.println(query);
		boolean result = service.hasAlias(query);

		System.out.println("중복유무 ::: " + result);
		return result;
	}

	@PutMapping("edit")
	public boolean update(
			// @RequestParam("grade") int gradeId,
			Authentication authentication,
			@RequestBody User user) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		user.setId(id);

		System.err.println(user);

		service.edit(user);

		User ModiUser = service.getById(id);
		System.out.println("수정 후 유저정보 " + ModiUser);

		System.out.println("수정완료");

		return true;
	}

	//UUID생성
	public static String getUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@PostMapping
	public String update(
			User user,
			MultipartFile img, // 엔티티의 img 속성명과 같으면 안된다.
			Authentication authentication) throws IOException {
		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		// 파일명 인코딩
		String fileName = img.getOriginalFilename();
		String encodedFileName = new String(fileName.getBytes("8859_1"),"UTF-8");

		String ext = encodedFileName.substring(encodedFileName.lastIndexOf("."));
		String savedFileName = getUuid() + ext; // 저장되는 파일 이름
		log.info("savedFileName= {}", savedFileName);


		// 파일 생성 및 데이터 저장
		File file = new File( fileDir + File.separator+ savedFileName);
		img.transferTo(file);

		user.setProfileImg(savedFileName);
		user.setId(id);

		service.edit(user);

		return "변환성공";

	}

	 @GetMapping("reg")
	 public Boolean reg(
	 	@RequestParam(name="q", required = false) String email,
		@RequestParam(name="a", required = false) String alias
	 ){
		if(alias == null) {
			 System.out.println("이메일 검색어:" + email);
			 Boolean hasEmail = service.hasEmail(email);
			 System.out.println("이메일 유무 :" + hasEmail);
			return hasEmail;
		}
		else {
			System.out.println("닉네임 검색어:" + alias);
			Boolean hasAlias = service.hasAlias(alias);
			System.out.println("닉네임 유무 :" + hasAlias);
			return hasAlias;
		 }

	 }

}