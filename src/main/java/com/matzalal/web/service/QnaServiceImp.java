package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Question;
import com.matzalal.web.repository.QuestionRepository;

@Service
public class QnaServiceImp implements QnaService {

	@Autowired
	private QuestionRepository repository;
	
	@Override
	public Question add(Question question) {
        repository.save(question);
        Question addQna = repository.last();
        return addQna;
	}

	@Override
	public List<Question> getList() {
		return repository.findAll();
	}

	@Override
	public int countQna() {
		return repository.count();
	}

	@Override
	public List<Question> getListByPage(Integer offset, Integer page, Integer size, Long questionId, String query) {  //입력받을 값.

		List<Question> list = repository.getListByPage(offset, page, size, questionId, query);  //입력받을 값.
		return list;
	}

	@Override
	public Question getById(Long questionId) {
		return repository.findById(questionId);
	}

	@Override
	public void edit(Question question) {
		repository.modify(question);		
	}
	
	
}
