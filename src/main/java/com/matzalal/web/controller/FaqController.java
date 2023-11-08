package com.matzalal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.entity.Question;
import com.matzalal.web.service.FaqService;

@Controller
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private FaqService service;

	@RequestMapping("list")
	public String list(Model model) {
		List<Faq> list = service.getListByPage(1,null); //1페이지, 검색어x
		int count = service.countFaq();
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		
		System.out.println(list);
		System.out.println("FAQ 총 "+count+"건");
		
		return "faq/list";
	}
	
	@RequestMapping("detail")
	public String detail(Model model, 
			@RequestParam(name="question-id") Long faqId
			//@RequestParam(name="prev-question-id") Long prevquestionId,
			//@RequestParam(name="next-question-id") Long nextquestionId
			){
		
		
		Faq faq = service.getById(faqId);
		System.out.println("faq ID? " +faqId);
		/*
		//prevquestionId = questionId - 1;
		//nextquestionId = questionId + 1;
		//System.out.println("이전 노티스 ID? " +prevquestionId);
		//System.out.println("다음 노티스 ID? " +nextquestionId);
		*/
		
		model.addAttribute("faq", faq);
		
		System.out.println("faq 다 담아오니? " +faq);

		return "faq/detail";
	}
}