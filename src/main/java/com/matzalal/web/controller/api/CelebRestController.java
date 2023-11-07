package com.matzalal.web.controller.api;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.entity.CelebRestView;
import com.matzalal.web.service.CelebListService;
import com.matzalal.web.service.CelebRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiCelebController")
@RequestMapping("/api/celebs")
public class CelebRestController {

    @Autowired
    private CelebRestService celebRestService;
    @Autowired
    private CelebListService celebListService;

    @GetMapping
    public List<CelebRestView> list(@RequestParam(name="c", required = false) Long celebId
    ){
        List<CelebRestView> celebRestList = celebRestService.getCelebListViewById(celebId);
        return celebRestList;
    }
//    @GetMapping("{id}")
//    public Menu detail(@PathVariable long id
////        , @PathVariable String name
//    ){
//        Menu menu = service.getById(id);
//        return menu;
//    }

}
