package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Question {
	private Long questionId;
	private String title;
	private String content;
	private String img;
	private Date createdDate;
	private Long userId;
	private Integer open;
	}