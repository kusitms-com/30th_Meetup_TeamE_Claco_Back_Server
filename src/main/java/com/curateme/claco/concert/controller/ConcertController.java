package com.curateme.claco.concert.controller;

import com.curateme.claco.concert.domain.dto.request.ConcertLikesRequest;
import com.curateme.claco.concert.domain.dto.response.ConcertDetailResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertLikedResponse;
import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.service.ConcertService;
import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.global.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping("/views/{categoryName}/{direction}")
    @Operation(summary = "공연 둘러보기", description = "기능명세서 화면번호 4.0.0")
    @Parameter(name = "categoryName", description = "카테고리 명", required = true, example = "grand")
    @Parameter(name = "direction", description = "정렬 순서", required = true, example = "asc/dsc")
    public ApiResponse<PageResponse<ConcertResponse>> getConcerts(
        @PathVariable("categoryName") String categoryName,
        @PathVariable("direction") String direction,
        @RequestParam("page") int page,
        @RequestParam(value = "size", defaultValue = "9") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return ApiResponse.ok(concertService.getConcertInfos(categoryName, direction, pageable));
    }

    @GetMapping("/filters")
    @Operation(summary = "공연 둘러보기 세부사항 필터", description = "기능명세서 화면번호 4.0.1")
    public ApiResponse<PageResponse<ConcertResponse>> filterConcerts(
        @RequestParam("minPrice") Double minPrice,
        @RequestParam("maxPrice") Double maxPrice,
        @RequestParam("area") String area,
        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate endDate,
        @RequestParam("direction") String direction,
        @RequestParam("page") int page,
        @RequestParam(value = "size", defaultValue = "9") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return ApiResponse.ok(concertService.getConcertInfosWithFilter(minPrice, maxPrice, area, startDate, endDate, direction, pageable));
    }

    @GetMapping("/queries")
    @Operation(summary = "공연 둘러보기 검색하기", description = "기능명세서 화면번호 4.1.0")
    public ApiResponse<PageResponse<ConcertResponse>> searchConcerts(
        @RequestParam("query") String query,
        @RequestParam("direction") String direction,
        @RequestParam("page") int page,
        @RequestParam(value = "size", defaultValue = "9") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return ApiResponse.ok(concertService.getSearchConcert(query,direction, pageable));
    }

    @GetMapping("/details/{concertId}")
    @Operation(summary = "공연 상세보기", description = "기능명세서 화면번호 3.0.0")
    public ApiResponse<ConcertDetailResponse> getConcertDetails(
        @PathVariable("concertId") Long concertId
    ) {
        return ApiResponse.ok(concertService.getConcertDetailWithCategories(concertId));
    }

    @PostMapping("/likes")
    @Operation(summary = "공연 좋아요", description = "특정 공연에 좋아요를 추가합니다")
    public ApiResponse<String> postLikes(
        @RequestBody ConcertLikesRequest concertLikesRequest
    ) {
        return ApiResponse.ok(concertService.postLikes(concertLikesRequest));
    }

    @GetMapping("/likes")
    @Operation(summary = "내가 좋아요한 공연", description = "기능명세서 화면번호 7.3.0")
    public ApiResponse<List<ConcertLikedResponse>> getMyConcerts(
        @RequestParam("query") String query,
        @RequestParam("genre") String genre
    ) {
        return ApiResponse.ok(concertService.getLikedConcert(query, genre));
    }

}

