package com.curateme.claco.review.domain.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.curateme.claco.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.10.28
 * @author devkeon(devkeon123@gmail.com)
 * @details : TicketReview & PlaceCategory 다대다 해결 엔티티
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
@SQLDelete(sql = "UPDATE place_review SET active_status = 'DELETED' WHERE place_review_id = ?")
@SQLRestriction("active_status <> 'DELETED'")
public class PlaceReview extends BaseEntity {

	@Id
	@Column(name = "place_review_id")
	private Long id;

	// 장소평 카테고리 다대일 단방향 매핑
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_category_id")
	private PlaceCategory placeCategory;

	// 티켓 리뷰 다대일 양방향 매핑
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_review_id")
	private TicketReview ticketReview;

	// 연관관계 편의 메서드
	public void updateTicketReview(TicketReview ticketReview) {
		if (this.ticketReview != ticketReview) {
			this.ticketReview = ticketReview;
		}
		if (!ticketReview.getPlaceReviews().contains(this)) {
			ticketReview.addPlaceReview(this);
		}
	}

}
