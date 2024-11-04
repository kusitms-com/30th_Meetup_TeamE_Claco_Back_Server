package com.curateme.claco.preference.domain.vo;

import com.curateme.claco.preference.domain.entity.RegionPreference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionPreferenceVO {

	private String preferenceRegion;

	public static RegionPreferenceVO fromEntity(RegionPreference regionPreference) {
		return new RegionPreferenceVO(regionPreference.getRegionName());
	}

}
