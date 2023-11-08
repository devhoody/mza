package com.matzalal.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.service.QnaService;

@Controller("adminQnAController")
@RequestMapping("/admin/QnA")
public class QnAController {
	
	@Autowired
	private QnaService qnaService;

	@GetMapping("list")
	public String QnA(
			@RequestParam(defaultValue = "1") int page, Model model
	) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
//		List<Qna> qnaList = qnaService.getListByPage(offset, page, size, null, null);
//		int count = qnaService.countQna();
//		int pageCount = count / size;
		
//		model.addAttribute("qnaList", qnaList);
//		model.addAttribute("count", count);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("pageCount", pageCount);
//		
//		System.out.println("리스트 중에 어디 눌렀닝?!"+ qnaList);
//		System.out.println("QNA 총 "+count+"건");
//		
		return "/admin/QnA/list";
	}
	
	@RequestMapping("answer")
	public String answer() {
		return "/admin/QnA/answer";
	}

}
