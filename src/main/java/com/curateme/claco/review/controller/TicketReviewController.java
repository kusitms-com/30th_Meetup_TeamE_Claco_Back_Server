package com.curateme.claco.review.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.review.domain.dto.request.OrderBy;
import com.curateme.claco.review.domain.dto.request.TicketReviewCreateRequest;
import com.curateme.claco.review.domain.dto.response.CountResponse;
import com.curateme.claco.review.domain.dto.response.ReviewInfoResponse;
import com.curateme.claco.review.domain.dto.response.ReviewListResponse;
import com.curateme.claco.review.domain.dto.response.TicketListResponse;
import com.curateme.claco.review.domain.dto.response.TicketReviewInfoResponse;
import com.curateme.claco.review.domain.vo.ImageUrlVO;
import com.curateme.claco.review.service.TicketReviewService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.11.04
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.04		   이 건		   최초 생성
 * 2024.11.05   	   이 건        Swagger 적용
 */
@RestController
@RequestMapping("/api/ticket-reviews")
@RequiredArgsConstructor
public class TicketReviewController {

	private final TicketReviewService ticketReviewService;

	/**
	 * TicketReview 생성
	 * @param request : 생성 정보, 클라코북의 경우 초기 생성이라면 null
	 * @param files : 리뷰 이미지 배열
	 * @return : 생성한 TicketReview 정보
	 */
	@PostMapping(consumes = {"multipart/form-data"})
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<TicketReviewInfoResponse> createTicketReview(
		@Validated @RequestPart("body") TicketReviewCreateRequest request,
		@RequestPart("files") MultipartFile[] files) throws IOException {

		return ApiResponse.ok(ticketReviewService.createTicketReview(request, files));
	}

	/**
	 * 티켓 이미지 업로드
	 * @param id : 티켓 이미지를 저장할 TicketReview id
	 * @param file : 이미지 파일
	 * @return : 저장한 이미지 url
	 */
	@PutMapping("/ticket-images")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<ImageUrlVO> addNewTicketImage(
		@RequestParam("id") Long id,
		@RequestPart("file") MultipartFile file) throws IOException {

		return ApiResponse.ok(ticketReviewService.addNewTicket(id, file));
	}

	/**
	 * 단일 리뷰 상세 조회
	 * @param id : 조회하고자 하는 리뷰 id
	 * @return : 리뷰의 정보
	 */
	@GetMapping("/reviews/{reviewId}")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<ReviewInfoResponse> readDetailReview(@PathVariable("reviewId") Long id) {
		return ApiResponse.ok(ticketReviewService.readReview(id));
	}

	/**
	 * TicketReview 정보 상세 조회(공연 정보 포함)
	 * @param id : 조회하고자 하는 TicketReview id
	 * @return : TickerReview 전체 정보
	 */
	@GetMapping("/{ticketReviewId}")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<TicketReviewInfoResponse> readTicketReview(@PathVariable("ticketReviewId") Long id) {
		return ApiResponse.ok(ticketReviewService.readTicketReview(id));
	}

	/**
	 * 공연의 리뷰 리스트 조회
	 * @param concertId : 조회하고자 하는 공연 id
	 * @param page : 페이지 번호 (1부터 시작)
	 * @param size : 한 페이지의 크기 (10개 초과시 오류)
	 * @param orderBy : 정렬 조건 (별점 높은 순, 낮은 순, 최신순)
	 * @return : 총 페이지 개수, 현재 페이지 번호, 요청 사이즈, 페이징 된 데이터
	 */
	@GetMapping("/concerts/reviews/{concertId}")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<ReviewListResponse> readReviewOfConcert(@PathVariable("concertId") Long concertId,
		@Validated @Min(value = 1) @RequestParam("page") Integer page,
		@Validated @Max(value = 10) @RequestParam("size") Integer size,
		@RequestParam("orderBy")OrderBy orderBy
		) {
		return ApiResponse.ok(ticketReviewService.readReviewOfConcert(concertId, page - 1, size, orderBy));
	}

	/**
	 * 클라코북에 속한 티켓 조회
	 * @param id : 조회하고자 하는 클라코북 id
	 * @return : 티켓 아이디, 티켓 이미지
	 */
	@GetMapping("/claco-books/{clacoBookId}")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<TicketListResponse> readTicketListFromClacoBook(@PathVariable("clacoBookId") Long id) {
		return ApiResponse.ok(ticketReviewService.readTicketOfClacoBook(id));
	}

	/**
	 * 공연의 총 리뷰 개수
	 * @param id : 조회하고자 하는 공연 id
	 * @return : 리뷰 총 개수
	 */
	@GetMapping("/concerts/{concertId}/size")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<CountResponse> countReviewOfConcert(@PathVariable("concertId") Long id) {
		return ApiResponse.ok(new CountResponse(ticketReviewService.countReview(id)));
	}

	/**
	 * TickerReview 삭제 (Cascade)
	 * @param id : 삭제하고자 하는 TickerReview id
	 * @return : Void
	 */
	@DeleteMapping("/{ticketReviewId}")
	@Operation(summary = "ClacoBook", description = "기능명세서")
	public ApiResponse<Void> deleteTicketReview(@PathVariable("ticketReviewId") Long id) {
		ticketReviewService.deleteTicket(id);
		return ApiResponse.ok();
	}

}
