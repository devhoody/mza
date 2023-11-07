package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Report {
	
	private String reportGbn; // 신고 구분
	private Long reportSeq; // 신고 순번
	private Date createdDate; // 신고날짜
	private String content; // 신고내용
	private Integer processYn; // 처리 여부
	private Date processDate; // 처리 날짜
	private String adminName; // 관리자 닉네임
	private String reportUserName; // 신고자 닉네임
	private String tbl; // 신고대상 닉네임 or 게시글 제목 or 댓글 내용
	private String reportReason; // 신고사유

}
