package com.curateme.claco.review.domain.vo;

import com.curateme.claco.review.domain.entity.TagCategory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagCategoryVO {

	private Long tagCategoryId;
	private String tagName;

	public static TagCategoryVO fromEntity(TagCategory tagCategory) {
		return new TagCategoryVO(tagCategory.getId(), tagCategory.getName());
	}

}
