package com.curateme.claco.review.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.curateme.claco.global.entity.BaseEntity;
import com.curateme.claco.member.domain.entity.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.10.28
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.28		   이 건		   최초 생성
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE ticket_review SET active_status = 'DELETED' WHERE member_id = ?")
@SQLRestriction("active_status <> 'DELETED'")
public class TicketReview extends BaseEntity {

	@Id
	@Column(name = "ticket_review_id")
	private Long id;

	// 리뷰 장소평 일대다 양방향 매핑
	@OneToMany(mappedBy = "ticketReview", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PlaceReview> placeReviews;

	// 리뷰 이미지 일대다 양방향 매핑
	@OneToMany(mappedBy = "ticketReview", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ReviewImage> reviewImages;

	// 다대일 양방향 매핑
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	// 관람 회차
	@NotNull
	private String watchRound;
	// 관람 일자
	@NotNull
	private LocalDate watchDate;
	// 관람 좌석
	@NotNull
	private String watchSit;
	// 별점
	@NotNull
	@Column(precision = 2, scale = 1)
	private BigDecimal starRate;
	// 티켓 이미지 (클라코 생성)
	private String ticketImage;
	// 리뷰 내용
	private String content;

	// 연관관계 편의 메서드
	public void updateMember(Member member) {
		if (this.member != member) {
			this.member = member;
		}
		if (!member.getTicketReviews().contains(this)) {
			member.addTicketReview(this);
		}
	}

	// 연관관계 편의 메서드
	public void addPlaceReview(PlaceReview placeReview) {
		if (!this.placeReviews.contains(placeReview)) {
			this.placeReviews.add(placeReview);
		}
		if (placeReview.getTicketReview() != this) {
			placeReview.updateTicketReview(this);
		}
	}

	// 연관관계 편의 메서드
	public void addReviewImage(ReviewImage reviewImage) {
		if (!this.reviewImages.contains(reviewImage)) {
			this.reviewImages.add(reviewImage);
		}
		if (reviewImage.getTicketReview() != this) {
			reviewImage.updateTicketReview(this);
		}
	}


}
