package com.curateme.claco.preference.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curateme.claco.authentication.domain.JwtMemberDetail;
import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.member.domain.entity.Gender;
import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.domain.entity.Role;
import com.curateme.claco.member.repository.MemberRepository;
import com.curateme.claco.preference.domain.entity.Preference;
import com.curateme.claco.preference.domain.entity.RegionPreference;
import com.curateme.claco.preference.domain.entity.TypePreference;
import com.curateme.claco.preference.domain.vo.CategoryPreferenceVO;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.TypePreferenceVO;
import com.curateme.claco.preference.repository.PreferenceRepository;
import com.curateme.claco.preference.repository.RegionPreferenceRepository;
import com.curateme.claco.preference.repository.TypePreferenceRepository;

@ExtendWith(MockitoExtension.class)
class PreferenceServiceTest {

	@Mock
	private PreferenceRepository preferenceRepository;
	@Mock
	private SecurityContextUtil securityContextUtil;
	@Mock
	private MemberRepository memberRepository;
	@Mock
	private TypePreferenceRepository typePreferenceRepository;
	@Mock
	private RegionPreferenceRepository regionPreferenceRepository;
	@InjectMocks
	private PreferenceServiceImpl preferenceService;

	@Test
	@DisplayName("선호도 정보 생성 메서드 테스트")
	void savePreferenceTest() {
		// Given
		Long testId = 1L;
		String testString = "test";

		JwtMemberDetail jwtMemberDetail = mock(JwtMemberDetail.class);

		Member testMember = Member.builder()
			.email("test")
			.socialId(testId)
			.role(Role.MEMBER)
			.build();

		List<String> stringList = List.of("test1", "test2", "test3", "test4", "test5");

		SignUpRequest testRequest = SignUpRequest.builder()
			.nickname(testString)
			.age(10)
			.gender(Gender.MALE)
			.minPrice(10)
			.maxPrice(100)
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

		doReturn(jwtMemberDetail).when(securityContextUtil).getContextMemberInfo();
		doReturn(testId).when(jwtMemberDetail).getMemberId();
		doReturn(Optional.of(testMember)).when(memberRepository).findById(testId);
		when(preferenceRepository.save(any(Preference.class))).thenAnswer(invocation -> {
			Preference preference = invocation.getArgument(0);
			return Preference.builder()
				.id(testId)
				.member(preference.getMember())
				.minPrice(preference.getMinPrice())
				.maxPrice(preference.getMaxPrice())
				.preference1(preference.getPreference1())
				.preference2(preference.getPreference2())
				.preference3(preference.getPreference3())
				.preference4(preference.getPreference4())
				.preference5(preference.getPreference5())
				.build();
		});
		when(typePreferenceRepository.save(any(TypePreference.class))).thenReturn(null);
		when(regionPreferenceRepository.save(any(RegionPreference.class))).thenReturn(null);

		// When
		Preference preference = preferenceService.savePreference(testRequest);

		// Then
		verify(securityContextUtil).getContextMemberInfo();
		verify(jwtMemberDetail).getMemberId();
		verify(memberRepository).findById(testId);
		verify(preferenceRepository).save(any(Preference.class));

		assertThat(preference.getId()).isEqualTo(testId);
		assertThat(preference.getMember()).isEqualTo(testMember);

	}

}