package com.matzalal.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.service.FaqService;

@RestController("apiFaqController")
@RequestMapping("/api/faqs")
public class FaqController {
		
		@Autowired
		private FaqService service;
		
		//목록 제공 API
		@GetMapping
		//@CrossOrigin(origins = "http://127.0.0.1:5501/") // 메서드에서 설정
	    public List<Faq> list(
//	    	@RequestParam(name = "p", defaultValue = "1") Integer page,
//	    	@RequestParam(name = "c", required = false) Long categoryId,	    	
	    	@RequestParam(name = "q", required = false) String query
	    	) {
			
	        List<Faq> list = service.getList();
	        return list;
	    }

}
