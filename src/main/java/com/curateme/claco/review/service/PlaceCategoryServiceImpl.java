package com.curateme.claco.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.curateme.claco.review.domain.dto.response.PlaceCategoryInfoResponse;
import com.curateme.claco.review.repository.PlaceCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceCategoryServiceImpl implements PlaceCategoryService{

	private final PlaceCategoryRepository placeCategoryRepository;

	@Override
	public List<PlaceCategoryInfoResponse> readPlaceCategoryList() {

		return placeCategoryRepository.findAll().stream()
			.map(PlaceCategoryInfoResponse::fromEntity)
			.toList();
	}
}
