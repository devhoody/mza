package com.matzalal.web.controller;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Review;
import com.matzalal.web.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private HomeService service;

	@RequestMapping("index")
	public String index(Model model) {


		List<RecomView> recomViewList = service.getRecomViewList();
		List<Review> reviewList = service.getReviewList();
		List<RatingView> ratingViewList = service.getRankingViewList();

		System.out.println(recomViewList);
		System.out.println(reviewList);

		model.addAttribute("recom", recomViewList);
		model.addAttribute("review", reviewList);
		model.addAttribute("ratingView", ratingViewList);


		return "index";
	}
	@RequestMapping("rank")
	public String rank(Model model) {

		List<RatingView> ranking = service.getRankingViewListAll();

		model.addAttribute("ranking", ranking);


		return "menu/rank";
	}
}
