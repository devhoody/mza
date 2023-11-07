package com.matzalal.web.repository;

import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeRepository {
    List<RecomView> findRecomViewAll();

    List<Review> findReview();
}
