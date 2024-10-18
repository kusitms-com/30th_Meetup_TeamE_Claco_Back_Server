package com.curateme.claco.member.service;

import com.curateme.claco.member.domain.dto.request.SignUpRequest;
import com.curateme.claco.member.domain.dto.response.NicknameValidResponse;

/**
 * @author      : 이 건
 * @date        : 2024.10.18
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.18   	   이 건        최초 생성
 */
public interface MemberService {

	/**
	 * 닉네임 유효성 체크 (중복 검사)
	 * @param nickname : 검사하고자 하는 닉네임
	 * @return : True=사용 가능, False=사용 불가
	 */
	NicknameValidResponse checkNicknameValid(String nickname);

	/**
	 * 취향 정보, 닉네임 정보를 받아와 회원가입을 완료
	 * @param signUpRequest: nickname, gender, price, preference 정보
	 * @return : void (응답 코드만)
	 */
	Void signUp(SignUpRequest signUpRequest);

}
