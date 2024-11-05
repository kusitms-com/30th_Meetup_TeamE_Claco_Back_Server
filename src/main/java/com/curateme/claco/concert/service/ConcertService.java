package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.response.ConcertDetailResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.global.response.PageResponse;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;

public interface ConcertService {
    PageResponse<ConcertResponse> getConcertInfos(String categoryName, String direction, Pageable pageable);

    PageResponse<ConcertResponse> getConcertInfosWithFilter(Double minPrice, Double maxPrice, String area, LocalDate startDate, LocalDate endDate,
        String direction, Pageable pageable);

    PageResponse<ConcertResponse> getSearchConcert(String query, String direction, Pageable pageable);

    ConcertDetailResponse getConcertDetailWithCategories(Long concertId);

}
