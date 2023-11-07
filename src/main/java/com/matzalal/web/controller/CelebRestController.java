package com.matzalal.web.controller;

import com.matzalal.web.entity.*;
import com.matzalal.web.service.CelebListService;
import com.matzalal.web.service.CelebRestService;
import com.matzalal.web.service.RestDetailViewService;
import com.matzalal.web.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/celeb")
public class CelebRestController {

    @Autowired
    private CelebRestService celebRestService;
    @Autowired
    private CelebListService celebListService;
    @Autowired
    private RestDetailViewService service;
    @Autowired
    private ReviewService reviewService;

//    -----------------------------유명인-----------------------------------
    @RequestMapping("list")
    public String list(Model model){

        List<CelebRestView> celebRestList = celebRestService.getCelebListViewById(null);
        List<Celeb> celebCategoryList = celebListService.getCelebList();
//        List<CelebRestView> CelebRestViewList = celebRestService.getViewAll();

        model.addAttribute("celebRestList", celebRestList);
        model.addAttribute("celebCategoryList", celebCategoryList);

//        model.addAttribute("CelebRestViewList", CelebRestViewList);

        return "menu/celeb-list";
    }
//    ---------------------------------------------------------------------------------------
    @GetMapping("detail")
    public String detail(Model model,
                         @RequestParam(value = "rId") Long restId) {

        RestDetailView restDetailList = service.getRestDetailViewByid(restId);
        List<Review> reviewList = reviewService.getReviewAllByRestId(restId);
        System.out.println(restDetailList);
        System.out.println(reviewList);
        model.addAttribute("restDetail", restDetailList);
        model.addAttribute("reviewList", reviewList);

        return "menu/restDetail/rest_detail";
    }
}
