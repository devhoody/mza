package com.matzalal.web.service;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;

import java.util.List;

public interface HomeService {


    List<RecomView> getRecomViewList();

    List<Review> getReviewList();

    List<RatingView> getRankingViewList();

    List<RatingView> getRankingViewListAll();
    
    // 별점 랭킹순 3개 조회
    List<RatingView> getRankingList();
}
