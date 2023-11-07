package com.matzalal.web.service;

import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;

import java.util.List;

public interface HomeService {


    List<RecomView> getRecomViewList();

    List<Review> getReviewList();
}
