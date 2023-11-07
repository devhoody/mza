package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class RestView {
    private Integer restId;
    private String restName;
    private Date openTime;
    private Date closeTime;
    private String addr;
    private Integer locRestId;
    private String phone;
    private Integer menuId;
    private Long rating;
    private String bannerImg;
    private String img1;
    private String img2;
    private String img3;
    private Integer celebRestId;
    private Integer reviewId;


}
