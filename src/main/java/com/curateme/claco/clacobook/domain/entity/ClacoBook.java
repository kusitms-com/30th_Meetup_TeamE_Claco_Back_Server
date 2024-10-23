package com.curateme.claco.clacobook.domain.entity;

import com.curateme.claco.member.domain.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.10.23
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.23   	   이 건        최초 생성
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClacoBook {

	@Id @Column(name = "claco_book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 제목
	@NotNull
	private String title;
	// 클라코북 색깔
	@NotNull
	private String color;

	// Member 다대일 양방향 매핑 (주 테이블)
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

}
