package com.curateme.claco.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.domain.response.NicknameValidResponse;
import com.curateme.claco.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public NicknameValidResponse checkNicknameValid(String nickname) {

		Optional<Member> findMemberNickname = memberRepository.findMemberByNickname(nickname);

		return new NicknameValidResponse(findMemberNickname.isEmpty());
	}
}
