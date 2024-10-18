package com.curateme.claco.member.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.domain.entity.Role;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author      : 이 건
 * @date        : 2024.10.18
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.18   	   이 건        최초 생성
 */
@Slf4j
@Transactional
@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EntityManager entityManager;

	private final Role testRole = Role.MEMBER;
	private final Long testLong = 1L;
	private final String testString = "test";

	@Test
	@DisplayName("소셜 아이디로 멤버 찾기")
	void findMemberBySocialId() {
		// Given
		Member testMember = Member.builder()
			.email(testString)
			.nickname(testString)
			.role(testRole)
			.socialId(testLong)
			.profileImage(testString)
			.build();

		entityManager.persist(testMember);

		// When
		Optional<Member> assertMember = memberRepository.findMemberBySocialId(testLong);

		// Then
		assertThat(assertMember.isPresent()).isTrue();
		assertThat(assertMember.get()).isEqualTo(testMember);
		assertThat(assertMember.get().getSocialId()).isEqualTo(testMember.getSocialId());

	}

	@Test
	@DisplayName("닉네임으로 멤버 찾기")
	void findMemberByNickname() {
		// Given
		Member testMember = Member.builder()
			.email(testString)
			.nickname(testString)
			.role(testRole)
			.socialId(testLong)
			.profileImage(testString)
			.build();

		entityManager.persist(testMember);

		// When
		Optional<Member> assertMember = memberRepository.findMemberByNickname(testString);

		// Then
		assertThat(assertMember.isPresent()).isTrue();
		assertThat(assertMember.get()).isEqualTo(testMember);
		assertThat(assertMember.get().getNickname()).isEqualTo(testMember.getNickname());

	}
}