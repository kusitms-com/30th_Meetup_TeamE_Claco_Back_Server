package com.curateme.claco.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curateme.claco.global.response.ApiResponse;
import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.member.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * @author      : 이 건
 * @date        : 2024.10.22
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.22   	   이 건        최초 생성
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	/**
	 * GET /api/nickname
	 * 닉네임 유효성 체크
	 * @param nickname : 체크하고자 하는 닉네임
	 * @return : COM-000 정상, MEM-009 닉네임 중복
	 */
	@GetMapping("/nickname")
	public ApiResponse<Void> checkNicknameDuplicate(@RequestParam("nickname") String nickname) {
		memberService.checkNicknameValid(nickname);

		return ApiResponse.ok();
	}

	/**
	 * POST /api/sign-up
	 * 회원가입 정보 입력
	 * @param request : 회원가입하고자 하는 정보 (닉네임, 선호 정보)
	 * @return : COM-000 정상, MEM-009 닉네임 중복
	 */
	@PostMapping("/sign-up")
	public ApiResponse<Void> signUp(@RequestBody SignUpRequest request) {
		memberService.signUp(request);

		return ApiResponse.ok();
	}

}
