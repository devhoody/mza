package com.matzalal.web.controller;

import com.matzalal.web.entity.RestView;
import com.matzalal.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @RequestMapping("result")
    public String list(Model model, String query){
        List<RestView> list = service.getViewList(query);
        model.addAttribute("list", list);
        return "/search/search_result";
    }
}
