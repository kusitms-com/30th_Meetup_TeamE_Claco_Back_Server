package com.curateme.claco.review.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.curateme.claco.review.domain.entity.PlaceReview;
import com.curateme.claco.review.domain.entity.ReviewTag;
import com.curateme.claco.review.domain.entity.TicketReview;
import com.curateme.claco.review.domain.vo.ImageUrlVO;
import com.curateme.claco.review.domain.vo.PlaceCategoryVO;
import com.curateme.claco.review.domain.vo.TagCategoryVO;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewInfoResponse {

	// 생성한 TicketReview id
	private Long ticketReviewId;
	// 작성자 이름
	private String userName;
	// 작성자 프사
	private String profileImage;
	// 리뷰 남긴 일자
	private LocalDate createdDate;
	// 관람 좌석
	private String watchSit;
	// 별점
	private BigDecimal starRate;
	// 리뷰 내용
	private String content;
	// 이미지 url 리스트
	private List<ImageUrlVO> reviewImages;
	// 장소평 리스트
	private List<PlaceCategoryVO> placeReviews;
	// 공연 태그 리스트
	private List<TagCategoryVO> tagReviews;

	// 공연 성격 카테고리 제외 리뷰
	public static ReviewInfoResponse fromEntityToSimpleReview(TicketReview ticketReview) {
		return ReviewInfoResponse.builder()
			.ticketReviewId(ticketReview.getId())
			.userName(ticketReview.getMember().getNickname())
			.profileImage(ticketReview.getMember().getProfileImage())
			.createdDate(LocalDate.from(ticketReview.getCreatedAt()))
			.watchSit(ticketReview.getWatchSit())
			.starRate(ticketReview.getStarRate())
			.content(ticketReview.getContent())
			.reviewImages(
				ticketReview.getReviewImages().stream()
					.map(ImageUrlVO::fromReviewImage)
					.toList()
			)
			.placeReviews(ticketReview.getPlaceReviews().stream()
				.map(PlaceReview::getPlaceCategory)
				.map(PlaceCategoryVO::fromEntity)
				.toList()
			)
			.build();
	}

	// 공연 카테고리 포함 리뷰 (상세보기)
	public static ReviewInfoResponse fromEntityToDetailReview(TicketReview ticketReview) {
		return ReviewInfoResponse.builder()
			.ticketReviewId(ticketReview.getId())
			.userName(ticketReview.getMember().getNickname())
			.profileImage(ticketReview.getMember().getProfileImage())
			.createdDate(LocalDate.from(ticketReview.getCreatedAt()))
			.watchSit(ticketReview.getWatchSit())
			.starRate(ticketReview.getStarRate())
			.content(ticketReview.getContent())
			.reviewImages(
				ticketReview.getReviewImages().stream()
					.map(ImageUrlVO::fromReviewImage)
					.toList()
			)
			.placeReviews(ticketReview.getPlaceReviews().stream()
				.map(PlaceReview::getPlaceCategory)
				.map(PlaceCategoryVO::fromEntity)
				.toList()
			)
			.tagReviews(ticketReview.getReviewTags().stream()
				.map(ReviewTag::getTagCategory)
				.map(TagCategoryVO::fromEntity)
				.toList()
			)
			.build();
	}

}
