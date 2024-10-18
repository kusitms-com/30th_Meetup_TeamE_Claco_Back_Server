package com.curateme.claco.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.domain.entity.Role;
import com.curateme.claco.member.domain.response.NicknameValidResponse;
import com.curateme.claco.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;
	@InjectMocks
	private MemberService memberService;

	private final String testString = "test";
	private final Long testLong = 1L;

	@Test
	void checkNicknameValid() {
		// Given
		Member testMember = Member.builder()
			.email(testString)
			.nickname(testString)
			.socialId(testLong)
			.role(Role.MEMBER)
			.build();

		when(memberRepository.findMemberByNickname(testString)).thenReturn(Optional.of(testMember));

		// When
		NicknameValidResponse testResponse = memberService.checkNicknameValid(testString);

		// Then
		verify(memberRepository, times(1)).findMemberByNickname(testString);

		assertThat(testResponse.getCanUse()).isFalse();

	}

}