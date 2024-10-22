package com.curateme.claco.member.domain.dto.request;

import java.util.List;

import com.curateme.claco.member.domain.entity.Gender;
import com.curateme.claco.preference.domain.vo.CategoryPreferenceVO;
import com.curateme.claco.preference.domain.vo.RegionPreferenceVO;
import com.curateme.claco.preference.domain.vo.TypePreferenceVO;

import jakarta.validation.constraints.NotNull;
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

	@NotNull
	private String nickname;
	@NotNull
	private Gender gender;
	@NotNull
	private Integer age;
	@NotNull
	private Integer minPrice;
	@NotNull
	private Integer maxPrice;
	private List<RegionPreferenceVO> regionPreferences;
	private List<TypePreferenceVO> typePreferences;
	private List<CategoryPreferenceVO> categoryPreferences;

}
