package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RatingView {
    private Long rank;
    private Long restId;
    private String restName;
    private Long restAmount;
    private Long rating;
    private String img;
}
