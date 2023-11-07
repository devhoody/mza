package com.matzalal.web.repository;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeRepository {
    List<RecomView> findRecomViewAll();

    List<Review> findReview();

    List<RatingView> findRankLimitThree();

    List<RatingView> findRankAll();
    
    // 리뷰 랭킹순 3개 조회(위쪽 매퍼 오류있음)
    List<RatingView> findRankThree();
    
}
