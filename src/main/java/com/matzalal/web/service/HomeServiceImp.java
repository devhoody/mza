package com.matzalal.web.service;

import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;
import com.matzalal.web.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImp implements HomeService {

@Autowired
private HomeRepository homeRepository;

    @Override
    public List<RecomView> getRecomViewList() {
        List<RecomView> list = homeRepository.findRecomViewAll();
        return list;
    }

    @Override
    public List<Review> getReviewList() {
        List<Review> list = homeRepository.findReview();
        return list;
    }
}
