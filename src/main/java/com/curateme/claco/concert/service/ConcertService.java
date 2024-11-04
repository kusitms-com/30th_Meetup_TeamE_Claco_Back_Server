package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.global.response.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ConcertService {
    PageResponse<ConcertResponse> getConcertInfos(String categoryName, String direction, Pageable pageable);
}
