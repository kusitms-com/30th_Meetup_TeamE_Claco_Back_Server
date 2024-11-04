package com.curateme.claco.review.domain.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.11.04
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.04		   이 건		   최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewListResponse {

	// 총 페이지 수
	private Integer totalPage;
	// 현재 페이지
	private Integer currentPage;
	// 요청 페이지 크기
	private Integer size;
	// 리뷰 정보 리스트 (페이징 된)
	private List<ReviewInfoResponse> reviewList;

}
