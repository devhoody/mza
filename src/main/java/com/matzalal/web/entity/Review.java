package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private Long reviewId;
    private Long userId;
    private Long gradeId;
    private Long restId;
    private Long rating;
    private String content;
    private String bannerImg;
    private Date created_date;
    private String restName;
    private String alias;

//    private Long reviewId;
//    private Long userId;
//    private Long gradeId;
//    private Long restId;
//    private Long rating;
//    private String content;
//    private String img;
//    private Date createdDate;

}
