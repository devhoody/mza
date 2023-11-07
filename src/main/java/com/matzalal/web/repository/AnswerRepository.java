package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Question;

@Mapper
public interface AnswerRepository {

	void save(Question qna);

	Question last();

	List<Question> findAll();

	int count();

	List<Question> getListByPage(int offset, int page, int size, Long questionId, String query);

	Question findById(Long questionId);

	void modify(Question question);

}
