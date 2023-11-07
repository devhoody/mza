package com.matzalal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.PostLike;
import com.matzalal.web.repository.CommuLikeRepository;

@Service
public class CommuLikeServiceImp implements CommuLikeService {

	@Autowired
	CommuLikeRepository repository;
	
	@Override
	public PostLike add(PostLike like) {
		
		repository.save(like);
		
		PostLike newOne = repository.last();
		System.out.println(newOne);
		return newOne;

	}

	@Override
	public void delete(Long userId, Long postId ) {

		int rowCount =repository.delete(userId, postId);

	}
}
