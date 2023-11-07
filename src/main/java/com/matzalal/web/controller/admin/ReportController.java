package com.matzalal.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.ReportUser;
import com.matzalal.web.service.ReportCommentService;
import com.matzalal.web.service.ReportPostService;
import com.matzalal.web.service.ReportService;
import com.matzalal.web.service.ReportUserService;

@Controller("adminReportController")
@RequestMapping("/admin/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	// 회원 신고 목록 조회
	@GetMapping("list")
	public String uReportList(
			@RequestParam(defaultValue = "1") int page, Model model		
	) {
		// 페이지 계산 변수
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		
		// 목록 조회
		List<Report> reportList = reportService.getListByPage(offset, page, size, null);
		
		
		// 개수 조회
		int count = reportService.countReport();
		int pageCount = count / size;
		
		// 모델에 담아서 전달
		model.addAttribute("reportList", reportList);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println("리스트 중에 어디 눌렀닝?!"+ reportList);
		System.out.println("회원 신고 총 "+count+"건");
		
		return "/admin/report/list";
	}
	
	// 신고 처리페이지 조회
	@GetMapping("process")
	public String process() {
		System.out.println("공지사항 등록 페이지 조회");
		return "admin/report/process";
	}
		
	
}
