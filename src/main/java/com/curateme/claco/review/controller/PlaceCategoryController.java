package com.curateme.claco.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.review.domain.vo.PlaceCategoryVO;
import com.curateme.claco.review.service.PlaceCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/place-categories")
@RequiredArgsConstructor
public class PlaceCategoryController {

	private final PlaceCategoryService placeCategoryService;

	@GetMapping
	public ApiResponse<Map<String, List<PlaceCategoryVO>>> readPlaceCategoryList() {
		return ApiResponse.ok(Map.of("categoryList", placeCategoryService.readPlaceCategoryList()));
	}

}
