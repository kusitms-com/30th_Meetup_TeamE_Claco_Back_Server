package com.curateme.claco.preference.domain.entity;

import com.curateme.claco.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.10.22
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.22   	   이 건        최초 생성
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TypePreference extends BaseEntity {

	@Id
	@Column(name = "type_preference_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String typeContent;

	@ManyToOne
	@JoinColumn(name = "preference_id")
	private Preference preference;

	public static TypePreference of(String typeContent) {
		TypePreference typePreference = new TypePreference();
		typePreference.typeContent = typeContent;

		return typePreference;
	}

	// 연관관계 편의 메서드
	public void updatePreference(Preference preference) {
		if (this.preference != preference) {
			this.preference = preference;
		}
		if (!preference.getTypePreferences().contains(this)) {
			preference.addTypeReference(this);
		}
	}

}
