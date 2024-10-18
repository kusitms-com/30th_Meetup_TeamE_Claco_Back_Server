package com.curateme.claco.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curateme.claco.member.entity.Member;

/**
 * @packageName : com.curateme.claco.member.repository
 * @fileName    : MemberRepository.java
 * @author      : 이 건
 * @date        : 2024.10.17
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 	2024.10.17   	   이 건        최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	/**
	 * 소셜 id 로 Member 찾는 메서드
	 * @param socialId : OAuth 가입 시 발급된 socialId
	 * @return : Optional Member
	 */
	Optional<Member> findMemberBySocialId(Long socialId);

}
