package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportPost;

@Mapper
public interface ReportRepository {


	List<Report> getListByPage(Integer offset, Integer page, Integer size, String query);

	int count();

}
