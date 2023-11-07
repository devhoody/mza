package com.matzalal.web.service;

import com.matzalal.web.entity.RatingView;
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

    @Override
    public List<RatingView> getRankingViewList() {
        List<RatingView> list = homeRepository.findRankLimitThree();

        return list;
    }

    @Override
    public List<RatingView> getRankingViewListAll() {
        List<RatingView> list = homeRepository.findRankAll();

        return list;
    }

    // 별점 순으로 맛집 3개 랭킹 조회(일주일기준)
	@Override
	public List<RatingView> getRankingList() {
		List<RatingView> list = homeRepository.findRankAll();
		System.out.println("일주일간의 별점 순으로 3개의 맛집을 추려냅니다");
        return list;
	}
    
    
}
