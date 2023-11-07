package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingView {
    private Long rank;
    private Long restId;
    private String restName;
    private Long restAmount;
    private Long rating;
    private String img;
    private Date createdDate;
}
