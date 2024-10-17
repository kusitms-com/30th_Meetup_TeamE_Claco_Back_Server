package com.curateme.claco.member.entity;

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
 * 	2024.10.15   	   이 건        최초 생성
 * 	2024.10.16    	   이 건		   빌더 추가 및 MemberType 명칭 변경
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
	// 프로필 이미지 url
	private String profileImage;
	private String refreshToken;

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
