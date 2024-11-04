package com.curateme.claco.preference.domain.dto.request;

import java.util.List;

import com.curateme.claco.member.domain.entity.Gender;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.TypePreferenceVO;

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
public class PreferenceUpdateRequest {

	private Gender gender;
	private Integer age;
	private Integer minPrice;
	private Integer maxPrice;
	private List<RegionPreferenceVO> regionPreferences;
	private List<TypePreferenceVO> typePreferences;

}
