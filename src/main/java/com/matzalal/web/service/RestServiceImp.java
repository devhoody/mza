package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.Rest;
import com.matzalal.web.repository.ReportReasonRepository;
import com.matzalal.web.repository.ReportRepository;
import com.matzalal.web.repository.RestRepository;

@Service
public class RestServiceImp implements RestService {
	
	@Autowired
	private RestRepository repository;

	@Override
	public List<Rest> getList() {
		List<Rest> list = repository.getList();
		return list;
	}

	
	

}
