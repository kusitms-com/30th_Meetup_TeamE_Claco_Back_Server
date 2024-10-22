package com.curateme.claco.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curateme.claco.authentication.domain.JwtMemberDetail;
import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.global.exception.BusinessException;
import com.curateme.claco.global.response.ApiStatus;
import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.member.domain.entity.Gender;
import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.domain.entity.Role;
import com.curateme.claco.member.repository.MemberRepository;
import com.curateme.claco.preference.domain.entity.Preference;
import com.curateme.claco.preference.domain.vo.CategoryPreferenceVO;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.TypePreferenceVO;
import com.curateme.claco.preference.service.PreferenceService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author      : 이 건
 * @date        : 2024.10.22
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.22   	   이 건        최초 생성
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;
	@Mock
	private SecurityContextUtil securityContextUtil;
	@Mock
	private PreferenceService preferenceService;
	@InjectMocks
	private MemberServiceV1 memberService;

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
		BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
			memberService.checkNicknameValid(testString);
		});

		// Then
		verify(memberRepository, times(1)).findMemberByNickname(testString);
		assertThat(exception.getCode()).isEqualTo(ApiStatus.MEMBER_NICKNAME_DUPLICATE.getCode());

	}

	@Test
	void signUpTest() {
		// Given
		List<String> stringList = List.of("test1", "test2", "test3", "test4", "test5");

		Integer testInteger = 10;

		Member testMember = Member.builder()
			.id(testLong)
			.email(testString)
			.socialId(testLong)
			.role(Role.SOCIAL)
			.build();

		Preference preferenceMock = mock(Preference.class);

		SignUpRequest testRequest = SignUpRequest.builder()
			.nickname(testString)
			.age(testInteger)
			.gender(Gender.MALE)
			.minPrice(testInteger)
			.maxPrice(testInteger)
			.categoryPreferences(stringList.stream()
				.map(CategoryPreferenceVO::new)
				.toList())
			.regionPreferences(stringList.stream()
				.map(RegionPreferenceVO::new)
				.toList())
			.typePreferences(stringList.stream()
				.map(TypePreferenceVO::new)
				.toList())
			.build();

		JwtMemberDetail jwtMemberDetailMock = mock(JwtMemberDetail.class);

		when(securityContextUtil.getContextMemberInfo()).thenReturn(jwtMemberDetailMock);
		when(jwtMemberDetailMock.getMemberId()).thenReturn(testLong);
		when(memberRepository.findById(testLong)).thenReturn(Optional.of(testMember));
		when(preferenceService.savePreference(testRequest)).thenReturn(preferenceMock);

		// When
		memberService.signUp(testRequest);

		// Then
		verify(securityContextUtil).getContextMemberInfo();
		verify(jwtMemberDetailMock).getMemberId();
		verify(memberRepository).findById(testLong);
		verify(preferenceService).savePreference(testRequest);

		assertThat(testMember.getNickname()).isEqualTo(testString);
		assertThat(testMember.getPreference()).isEqualTo(preferenceMock);
		assertThat(testMember.getAge()).isEqualTo(testInteger);
		assertThat(testMember.getRole()).isEqualTo(Role.MEMBER);

	}
}