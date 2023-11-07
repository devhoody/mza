package com.matzalal.web.repository;

import com.matzalal.web.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewRepository {
//    List<Review> findReviewByRestId(Long restId);
	
	  List<Review> getReviewList();
}
