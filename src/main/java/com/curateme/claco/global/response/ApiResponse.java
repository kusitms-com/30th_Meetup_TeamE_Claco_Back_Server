package com.curateme.claco.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * 2024.10.14   	   이 건        간단하게 수정
 * 2024.10.22   	   이 건        ok 응답 오버로딩 메서드 추가 (result 없음)
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    @Schema(description = "응답 코드", example = "COM-000")
    private final String code;
    @Schema(description = "응답 메시지", example = "OK.")
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(ApiStatus.OK.getCode(), ApiStatus.OK.getMessage(), null);
    }

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