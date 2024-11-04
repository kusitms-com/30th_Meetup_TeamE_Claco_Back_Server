package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.repository.ConcertCategoryRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.global.response.PageResponse;
import java.util.List;
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
}

