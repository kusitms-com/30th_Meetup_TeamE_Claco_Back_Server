package com.curateme.claco.preference.domain.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

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
 * 2024.10.24   	   이 건        soft delete 조건 추가
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE region_preference SET active_status = 'DELETED' WHERE region_preference_id = ?")
@SQLRestriction("active_status <> 'DELETED'")
public class RegionPreference extends BaseEntity {

	@Id @Column(name = "region_preference_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 지역 이름
	private String regionName;
	@ManyToOne
	@JoinColumn(name = "preference_id")
	private Preference preference;

	// 정적 팩토리 메서드
	public static RegionPreference of(String regionName) {
		RegionPreference regionPreference = new RegionPreference();
		regionPreference.regionName = regionName;

		return regionPreference;
	}

	// 연관관계 편의 메서드
	public void updatePreference(Preference preference) {
		if (this.preference != preference) {
			this.preference = preference;
		}
		if (!preference.getRegionPreferences().contains(this)) {
			preference.addRegionPreference(this);
		}
	}

}
