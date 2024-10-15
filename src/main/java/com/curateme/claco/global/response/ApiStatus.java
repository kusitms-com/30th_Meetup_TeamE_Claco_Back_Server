package com.curateme.claco.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @packageName : com.curateme.claco.global.response
 * @fileName    : ApiStatus.java
 * @author      : 이 건
 * @date        : 2024.10.14
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 	2024.10.14   	   이 건        최초 생성
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiStatus {

	// 성공 응답
	OK(HttpStatus.OK, "COM-000", "Success request"),

	// 서버 에러
	EXCEPTION_OCCUR(HttpStatus.INTERNAL_SERVER_ERROR, "DBG-500", "Something went wrong."),
	RUNTIME_EXCEPTION_OCCUR(HttpStatus.INTERNAL_SERVER_ERROR, "DBG-501", "Something went wrong.")
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
