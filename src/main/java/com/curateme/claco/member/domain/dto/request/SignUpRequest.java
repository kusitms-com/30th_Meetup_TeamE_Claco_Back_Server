package com.curateme.claco.member.domain.dto.request;

import java.util.List;

import com.curateme.claco.member.domain.entity.Gender;
import com.curateme.claco.preference.domain.vo.CategoryPreferenceVO;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.StatePreferenceVO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

	private String nickname;
	private Gender gender;
	private Integer minPrice;
	private Integer maxPrice;
	private List<RegionPreferenceVO> regionPreferences;
	private List<StatePreferenceVO> statePreferences;
	private List<CategoryPreferenceVO> categoryPreferences;

}
