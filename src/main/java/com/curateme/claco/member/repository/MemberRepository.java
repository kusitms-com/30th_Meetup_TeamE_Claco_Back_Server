package com.curateme.claco.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curateme.claco.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findMemberBySocialId(Long socialId);

}
