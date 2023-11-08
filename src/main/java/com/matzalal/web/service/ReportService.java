package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Report;

public interface ReportService {

	// 신고 목록 가져오기
	List<Report> getListByPage(Integer offset, Integer page, Integer size, String query);

	// 신고 목록 개수
	int countReport();

	
}
