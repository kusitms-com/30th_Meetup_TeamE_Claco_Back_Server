package com.curateme.claco.preference.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.global.exception.BusinessException;
import com.curateme.claco.global.response.ApiStatus;
import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.repository.MemberRepository;
import com.curateme.claco.preference.domain.entity.Preference;
import com.curateme.claco.preference.domain.entity.RegionPreference;
import com.curateme.claco.preference.domain.entity.TypePreference;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.TypePreferenceVO;
import com.curateme.claco.preference.repository.PreferenceRepository;
import com.curateme.claco.preference.repository.RegionPreferenceRepository;
import com.curateme.claco.preference.repository.TypePreferenceRepository;

import lombok.RequiredArgsConstructor;
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
@Service
@Transactional
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

	private final SecurityContextUtil securityContextUtil;
	private final MemberRepository memberRepository;
	private final PreferenceRepository preferenceRepository;
	private final TypePreferenceRepository typePreferenceRepository;
	private final RegionPreferenceRepository regionPreferenceRepository;

	@Override
	public Preference savePreference(SignUpRequest signUpRequest) {

		// 현재 로그인 세션 유저 정보 추출
		Member member = memberRepository.findById(securityContextUtil.getContextMemberInfo().getMemberId()).stream()
			.findAny()
			.orElseThrow(() -> new BusinessException(ApiStatus.MEMBER_NOT_FOUND));

		// TODO: 개선 필요
		// Preference 생성
		Preference preference = Preference.builder()
			.member(member)
			.minPrice(signUpRequest.getMinPrice())
			.maxPrice(signUpRequest.getMaxPrice())
			.preference1(signUpRequest.getCategoryPreferences().get(0).getPreferenceCategory())
			.preference2(signUpRequest.getCategoryPreferences().get(1).getPreferenceCategory())
			.preference3(signUpRequest.getCategoryPreferences().get(2).getPreferenceCategory())
			.preference4(signUpRequest.getCategoryPreferences().get(3).getPreferenceCategory())
			.preference5(signUpRequest.getCategoryPreferences().get(4).getPreferenceCategory())
			.build();

		Preference savePreference = preferenceRepository.save(preference);

		// TypePreference 저장
		signUpRequest.getTypePreferences().stream()
			.map(TypePreferenceVO::getPreferenceType)
			.map(TypePreference::of)
			.forEach(typePreference -> {
				typePreference.updatePreference(savePreference);
				typePreferenceRepository.save(typePreference);
			});

		// RegionPreference 저장
		signUpRequest.getRegionPreferences().stream()
			.map(RegionPreferenceVO::getPreferenceRegion)
			.map(RegionPreference::of)
			.forEach(regionPreference -> {
				regionPreference.updatePreference(savePreference);
				regionPreferenceRepository.save(regionPreference);
			});

		return savePreference;
	}
}
