package com.curateme.claco.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.review.domain.dto.response.CategoryListResponse;
import com.curateme.claco.review.domain.vo.TagCategoryVO;
import com.curateme.claco.review.service.TagCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.11.03
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.03   	   이 건        최초 생성
 * 2024.11.05   	   이 건        Swagger 적용
 */
@RestController
@RequestMapping("/api/tag-categories")
@RequiredArgsConstructor
public class TagCategoryController {

	private final TagCategoryService tagCategoryService;

	@GetMapping
	@Operation(summary = "공연 유형의 카테고리 조회(한국어, 이미지 없음, 사용X)", description = "공연 유형의 카테고리 조회(한국어), 이미지 없음, 사용x")
	public ApiResponse<CategoryListResponse<List<TagCategoryVO>>> readTagCategoryList() {
		return ApiResponse.ok(new CategoryListResponse<>(tagCategoryService.readTagCategoryList()));
	}

}
