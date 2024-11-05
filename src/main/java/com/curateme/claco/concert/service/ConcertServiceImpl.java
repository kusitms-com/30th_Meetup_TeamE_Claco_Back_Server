package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.response.ConcertCategoryResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertDetailResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.domain.entity.Category;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.repository.CategoryRepository;
import com.curateme.claco.concert.repository.ConcertCategoryRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.global.response.PageResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertCategoryRepository concertCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PageResponse<ConcertResponse> getConcertInfos(String categoryName, String direction, Pageable pageable) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("prfpdfrom").ascending() : Sort.by("prfpdfrom").descending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        List<Long> concertIds = concertCategoryRepository.findConcertIdsByCategoryName(categoryName);

        Page<Concert> concertPage = concertRepository.findByIdIn(concertIds, sortedPageable);

        List<ConcertResponse> concertResponses = concertPage.getContent().stream()
            .map(ConcertResponse::fromEntity)
            .collect(Collectors.toList());

        return PageResponse.<ConcertResponse>builder()
            .listPageResponse(concertResponses)
            .totalCount(concertPage.getTotalElements())
            .size(concertPage.getSize())
            .build();
    }

    @Override
    public PageResponse<ConcertResponse> getConcertInfosWithFilter(Double minPrice, Double maxPrice,
        String area, LocalDate startDate, LocalDate endDate, String direction, Pageable pageable) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("prfpdfrom").ascending() : Sort.by("prfpdfrom").descending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        List<Long> concertIds = concertRepository.findConcertIdsByFilters(area, startDate, endDate);

        Page<Concert> concertPage = concertRepository.findByIdIn(concertIds, sortedPageable);

        List<ConcertResponse> concertResponses = concertPage.getContent().stream()
            .map(ConcertResponse::fromEntity)
            .collect(Collectors.toList());

        return PageResponse.<ConcertResponse>builder()
            .listPageResponse(concertResponses)
            .totalCount(concertPage.getTotalElements())
            .size(concertPage.getSize())
            .build();
    }

    @Override
    public PageResponse<ConcertResponse> getSearchConcert(String query, String direction, Pageable pageable) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("prfpdfrom").ascending() : Sort.by("prfpdfrom").descending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        List<Long> concertIds = concertRepository.findConcertIdsBySearchQuery(query);

        Page<Concert> concertPage = concertRepository.findByIdIn(concertIds, sortedPageable);

        List<ConcertResponse> concertResponses = concertPage.getContent().stream()
            .map(ConcertResponse::fromEntity)
            .collect(Collectors.toList());

        return PageResponse.<ConcertResponse>builder()
            .listPageResponse(concertResponses)
            .totalCount(concertPage.getTotalElements())
            .size(concertPage.getSize())
            .build();
    }

    @Override
    public ConcertDetailResponse getConcertDetailWithCategories(Long concertId) {

        Concert concert = concertRepository.findConcertById(concertId);

        List<Long> categoryIds = concertCategoryRepository.findCategoryIdsByCategoryName(concertId);
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        List<ConcertCategoryResponse> categoryResponses = categories.stream()
            .map(category -> new ConcertCategoryResponse(category.getCategory(), category.getImageUrl()))
            .collect(Collectors.toList());

        ConcertDetailResponse response = ConcertDetailResponse.fromEntity(concert, categoryResponses);

        return response;
    }
}

