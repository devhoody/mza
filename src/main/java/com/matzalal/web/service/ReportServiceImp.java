package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.ReportUser;
import com.matzalal.web.repository.ReportReasonRepository;
import com.matzalal.web.repository.ReportRepository;
import com.matzalal.web.repository.ReportUserRepository;
import com.matzalal.web.repository.ReportPostRepository;

@Service
public class ReportServiceImp implements ReportService {
	
	@Autowired
	private ReportRepository repository;
	
	@Autowired
	private ReportReasonRepository reportReasonRepository;
	
	// 회원 신고목록조회(페이지별)
	@Override
	public List<Report> getListByPage(Integer offset, Integer page, Integer size, String query) {
		List<Report> list = repository.getListByPage(offset, page, size, query);
		return list;
	}

	// 신고 개수 조회
	@Override
	public int countReport() {
		return repository.count();
	}

	// 신고 사유 조회
	@Override
	public List<ReportReason> getReasonList() {
		
		return reportReasonRepository.findAll();
	}



}
