package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Question;

@Mapper
public interface QuestionRepository {

	void save(Question question);

	Question last();

	List<Question> findAll();

	int count();

	List<Question> getListByPage(Integer offset, Integer page, Integer size, Long questionId, String query);

	Question findById(Long questionId);

	void modify(Question question);

}
