package com.curateme.claco.review.domain.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.curateme.claco.review.domain.vo.PlaceCategoryVO;
import com.curateme.claco.review.domain.vo.TagCategoryVO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class TicketReviewCreateRequest {

	// 공연 id
	@NotNull
	private Long concertId;
	// 만약 초기 생성이라면 null
	private Long clacoBookId;
	// 관람 날짜
	@NotNull
	private LocalDate watchDate;
	// 관람 회차
	@NotNull
	private String watchRound;
	// 관람 좌석
	private String watchSit;
	// 별점
	@NotNull
	@Max(5) @Min(0)
	private BigDecimal starRate;
	// 캐스팅
	@NotNull
	private String casting;
	// 감상평
	@NotNull
	@Size(max = 500)
	private String content;
	// 장소평
	@NotNull
	@Size(min = 5, max = 5)
	private List<PlaceCategoryVO> placeReviewIds;
	// 공연 감상 태그
	@NotNull
	@Size(min = 5, max = 5)
	private List<TagCategoryVO> tagCategoryIds;

}
