package com.matzalal.web.service;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.Review;
import com.matzalal.web.repository.ReportRepository;
import com.matzalal.web.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ReviewRepository repository;

    @Override
    public List<Review> getReviewAllByRestId(Long restId) {
        List<Review> reviewList = repository.findReviewByRestId(restId);
        return reviewList;
    }
}
