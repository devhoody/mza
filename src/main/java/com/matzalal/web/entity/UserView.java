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

public class UserView {
    private Long id;
    private String pwd;
    private String alias;
    private String email;
    private String phone;
    private String name;
    private Long gradeId;
    private String profileImg;
    private String date;
    private Long statusId;
    private Integer locationId;
    private Integer postCount;
    private Integer commentCount;
    private Integer favCount;
    private String gradeName;
    private String gradeImg;
    private Date sanctionTime;
}