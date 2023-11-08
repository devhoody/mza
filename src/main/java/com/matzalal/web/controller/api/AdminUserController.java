package com.matzalal.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.User;
import com.matzalal.web.service.UserService;

@RestController("adminApiUserController")
@RequestMapping("admin/api/users")
public class AdminUserController {

	@Autowired
	private UserService service;
	
	
	
// ~~~~~~~~~  관리자용  ~~~~~~~~
	
	
	@GetMapping
	public List<User> findUser(
			@RequestParam(name="p", defaultValue = "1") Integer page,
			@RequestParam(name="q", required = false) String query
			) {

		int pageSize = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * pageSize; // 시작 인덱스

		List<User> list = service.getList(offset, page, pageSize, query);
		
		return list;
	}
	
	// @ResponseBody
	
//	@GetMapping public List<User> list() {
//	
//		List<User> list = service.getList(); 
//		System.out.println("dd"); 
//		return list; 
//	}
	
//	@GetMapping("{id}") 
//	public User detail(@PathVariable Long id) {
//		System.out.println("cont1"); 
//		User user = service.getById(id);
//		System.out.println(user); 
//		System.out.println("~~~~");
//	
//	return user; 
//	}
//	
//
//
//	@PutMapping("{id}")
//	public void update(
//			@RequestBody User user) {
//		System.out.println(user);
//		service.edit(user);
//		System.out.println("api 수정");
//		
//	}
	
	@DeleteMapping("{ids}")
	public boolean delete(@PathVariable Long[] ids) {
		List<Long> idList = new ArrayList<>();
//		Long longValue = Long.parseLong(ids);
		
		if(ids.length > 1) {
			for(int i=0; i<ids.length; i++) {
				idList.add(ids[i]);
			}
			service.deleteUser(idList);
			System.out.println(idList);
			
		} else {
			System.out.println("1개 선택");
			service.deleteUser(ids[0]);
		}
			
//		System.out.println(idList);
		System.out.println("api 삭제완료");
		
		return true;
		
//		return "redirect:/admin/user/list";
	}

	
}
