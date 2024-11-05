package com.curateme.claco.member.domain.dto.response;

import com.curateme.claco.member.domain.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 이 건
 * @date : 2024.11.05
 * @author devkeon(devkeon123 @ gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.05   	   이 건        최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoResponse {

	// 닉네임
	private String nickname;
	// 이미지 Url
	private String imageUrl;

	public static MemberInfoResponse fromEntity(Member member) {
		return new MemberInfoResponse(member.getNickname(), member.getProfileImage());
	}

}
