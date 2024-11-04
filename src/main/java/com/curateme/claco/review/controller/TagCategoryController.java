package com.curateme.claco.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.review.domain.vo.TagCategoryVO;
import com.curateme.claco.review.service.TagCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tag-categories")
@RequiredArgsConstructor
public class TagCategoryController {

	private final TagCategoryService tagCategoryService;

	@GetMapping
	public ApiResponse<Map<String, List<TagCategoryVO>>> readTagCategoryList() {
		return ApiResponse.ok(Map.of("categoryList", tagCategoryService.readTagCategoryList()));
	}

}
