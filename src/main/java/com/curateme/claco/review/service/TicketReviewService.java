package com.curateme.claco.review.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.curateme.claco.review.domain.dto.request.OrderBy;
import com.curateme.claco.review.domain.dto.request.TicketReviewCreateRequest;
import com.curateme.claco.review.domain.dto.TicketReviewUpdateDto;
import com.curateme.claco.review.domain.dto.response.ReviewInfoResponse;
import com.curateme.claco.review.domain.dto.response.ReviewListResponse;
import com.curateme.claco.review.domain.dto.response.TicketListResponse;
import com.curateme.claco.review.domain.dto.response.TicketReviewInfoResponse;
import com.curateme.claco.review.domain.vo.ImageUrlVO;

/**
 * @author : 이 건
 * @date : 2024.11.04
 * @author devkeon(devkeon123 @ gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.11.04   	   이 건        최초 생성
 */
public interface TicketReviewService {

	/**
	 * 티켓 리뷰 생성 메서드(티켓 등록에서만 가능)
	 *
	 * @param request : 생성할 티켓&리뷰 정보들
	 * @return : 생성한 티켓&리뷰 정보
	 */
	TicketReviewInfoResponse createTicketReview(TicketReviewCreateRequest request, MultipartFile[] multipartFile)
		throws IOException;

	/**
	 * 티켓 생성 및 수정 메서드(티켓 이미지 처리)
	 *
	 * @param ticketReviewId : 티켓이 저장되는 티켓 리뷰 id
	 * @param multipartFile  : 티켓 이미지
	 * @return : 티켓 이미지 url
	 */
	ImageUrlVO addNewTicket(Long ticketReviewId, MultipartFile multipartFile) throws IOException;

	/**
	 * 리뷰 상세 조회 메서드 (티켓 정보 제외)
	 *
	 * @param reviewId : 조회하고자 하는 리뷰 id
	 * @return : 리뷰 상세 정보 (티켓 정보 제외)
	 */
	ReviewInfoResponse readReview(Long reviewId);

	/**
	 * 티켓 정보와 리뷰 정보 조회
	 *
	 * @param ticketReviewId : 티켓 리뷰 아이디
	 * @return : 티켓 리뷰 정보
	 */
	TicketReviewInfoResponse readTicketReview(Long ticketReviewId);

	/**
	 * 콘서트의 리뷰 리스트 조회
	 *
	 * @param concertId : 조회하고자 하는 콘서트 id
	 * @param page    : 조회하고자 하는 페이지 번호
	 * @param size      : 조회하고자 하는 리뷰 개수 (최대 10개)
	 * @return : 리뷰 정보 리스트, 페이지 정보
	 */
	ReviewListResponse readReviewOfConcert(Long concertId, Integer page, Integer size, OrderBy orderBy);

	/**
	 * 클라코북의 티켓 리스트 조회
	 * @param clacoBookId : 조회하고자 하는 클라코북 id
	 * @return : 티켓 이미지, 티켓 아이디
	 */
	TicketListResponse readTicketOfClacoBook(Long clacoBookId);

	/**
	 * 공연에 대한 리뷰 개수 조회
	 *
	 * @param concertId : 조회하고자 하는 콘서트 id
	 * @return : 리뷰의 총 개수
	 */
	Integer countReview(Long concertId);

	/**
	 * 리뷰 수정(생성) 메서드
	 *
	 * @param request : 수정할 리뷰 정보들
	 * @return : 수정한 리뷰 정보
	 */
	TicketReviewUpdateDto editTicketReview(TicketReviewUpdateDto request);

	/**
	 * 콘서트의 티켓 & 리뷰 삭제
	 *
	 * @param ticketReviewId : 삭제하려는 TicketReview id
	 */
	void deleteTicket(Long ticketReviewId);

}
