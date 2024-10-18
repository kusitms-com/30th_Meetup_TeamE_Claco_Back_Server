package com.curateme.claco.member.service;

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
	Boolean checkNicknameValid(String nickname);

}
