package com.curateme.claco.member.domain.entity;

import com.curateme.claco.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.curateme.claco.member.entity
 * @fileName    : Member.java
 * @author      : 이 건
 * @date        : 2024.10.15
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.15   	   이 건        최초 생성
 * 2024.10.16    	   이 건		   빌더 추가 및 MemberType 명칭 변경
 * 2024.10.17		   이 건		   엔티티 필드 제약 조건 변경
 * 2024.10.18		   이 건		   성별 필드 추가 (Gender)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	// auto_increment 사용 id
	@Id @Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String email;
	// 닉네임 (15글자 제약)
	@Column(unique = true, length = 15)
	private String nickname;
	// 소셜 id (카카오)
	@NotNull
	@Column(unique = true)
	private Long socialId;
	// 가입 타입
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private Role role;
	// 성별
	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	// 프로필 이미지 url
	private String profileImage;
	// refresh token
	private String refreshToken;

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
