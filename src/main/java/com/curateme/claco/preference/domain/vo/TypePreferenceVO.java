package com.curateme.claco.preference.domain.vo;

import com.curateme.claco.preference.domain.entity.TypePreference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypePreferenceVO {

	private String preferenceType;

	public static TypePreferenceVO fromEntity(TypePreference typePreference) {
		return new TypePreferenceVO(typePreference.getTypeContent());
	}

}
