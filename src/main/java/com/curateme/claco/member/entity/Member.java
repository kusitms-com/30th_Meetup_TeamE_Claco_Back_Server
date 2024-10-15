package com.curateme.claco.member.entity;

import com.curateme.claco.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	// auto_increment 사용 id
	@Id @Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 닉네임 (15글자 제약)
	@NotNull
	@Column(unique = true, length = 15)
	private String nickname;
	// 소셜 id (카카오)
	@NotNull
	private Long socialId;
	// 가입 타입
	@NotNull
	private MemberType memberType;
	// 프로필 이미지 url
	private String profileImage;

}
