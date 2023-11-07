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

//    private Long reviewId;
//    private Long userId;
//    private Long restId;
//    private Long rating;
//    private String content;
//    private String img;
//    private Date createdDate;

	private Long reviewId;
    private Long userId;
    private String alias;    
    private Long restId;
    private String restName;
    private Integer rating;
    private String content;
    private String img;
    private Date createdDate;

}
