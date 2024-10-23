package com.curateme.claco.preference.service;

import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.preference.domain.entity.Preference;

/**
 * @author      : 이 건
 * @date        : 2024.10.22
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.22   	   이 건        최초 생성
 */
public interface PreferenceService {

	/**
	 * 관련 Preference 객체를 생성하고 저장하는 메서드
	 * @param signUpRequest : 저장하고자 하는 Preference 정보를 종합적으로 담고 있는 객체
	 * @return : 저장한 Preference 반환 (Type, Region 제외)
	 */
	Preference savePreference(SignUpRequest signUpRequest);

}
