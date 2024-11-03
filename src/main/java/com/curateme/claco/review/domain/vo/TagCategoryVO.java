package com.curateme.claco.review.domain.vo;

import com.curateme.claco.review.domain.entity.TagCategory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.11.03
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.03		   이 건		   최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagCategoryVO {

	// id
	private Long tagCategoryId;
	// 태그 이름
	private String tagName;

	public static TagCategoryVO fromEntity(TagCategory tagCategory) {
		return new TagCategoryVO(tagCategory.getId(), tagCategory.getName());
	}

}
