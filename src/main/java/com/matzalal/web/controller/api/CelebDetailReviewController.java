package com.matzalal.web.controller.api;

import com.matzalal.web.entity.ReviewView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller("ReviewApiController")
@RequestMapping("/api/celeb/detail")
public class CelebDetailReviewController {
    @PostMapping
    public String update(ReviewView review,
                         MultipartFile img,
                         HttpServletRequest request) throws IOException {



        return;

    }
}
