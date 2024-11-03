package com.curateme.claco.review.domain.vo;

import com.curateme.claco.review.domain.entity.PlaceCategory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceCategoryVO {

	private Long placeCategoryId;
	private String categoryName;

	public static PlaceCategoryVO fromEntity(PlaceCategory placeCategory) {
		return new PlaceCategoryVO(placeCategory.getId(), placeCategory.getName());
	}

}
