package com.curateme.claco.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @packageName : com.curateme.claco.global.response
 * @fileName    : ApiResponse.java
 * @author      : 이 건
 * @date        : 2024.10.14
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 	2024.10.14   	   이 건        간단하게 수정
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(ApiStatus.OK.getCode(), ApiStatus.OK.getMessage(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> fail(ApiStatus apiStatus) {
        return new ApiResponse<>(apiStatus.getCode(), apiStatus.getMessage(), null);
    }
}