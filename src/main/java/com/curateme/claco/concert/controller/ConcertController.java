package com.curateme.claco.concert.controller;

import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.service.ConcertService;
import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.global.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping("/{categoryName}/{direction}")
    public ApiResponse<PageResponse<ConcertResponse>> getConcerts(
        @PathVariable("categoryName") String categoryName,
        @PathVariable("direction") String direction,
        @RequestParam("page") int page,
        @RequestParam(value = "size", defaultValue = "9") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return ApiResponse.ok(concertService.getConcertInfos(categoryName, direction, pageable));
    }
}

