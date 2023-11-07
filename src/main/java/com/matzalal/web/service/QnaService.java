package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Question;

public interface QnaService {

	Question add(Question question);

	List<Question> getList();

	int countQna();

	List<Question> getListByPage(Integer offset, Integer page, Integer size, Long questionId, String query);

	Question getById(Long questionId);

	void edit(Question question);

}
