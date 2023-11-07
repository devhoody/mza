package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Review;
import com.matzalal.web.repository.ReviewRepository;

@Service
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ReviewRepository repository;

	/*
	 * @Override public List<Review> getReviewAllByRestId(Long restId) {
	 * List<Review> reviewList = repository.findReviewByRestId(restId); return
	 * reviewList; }
	 */

	@Override
	public List<Review> getReviewList() {
		
		return repository.getReviewList();
	}
}
