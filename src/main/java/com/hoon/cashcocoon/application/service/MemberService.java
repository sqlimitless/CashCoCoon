package com.hoon.cashcocoon.application.service;

import com.hoon.cashcocoon.adapter.in.request.MemberRequest;
import com.hoon.cashcocoon.adapter.out.MemberEntityRepository;
import com.hoon.cashcocoon.adapter.out.persistance.MemberEntity;
import com.hoon.cashcocoon.application.port.in.MemberPortIn;
import com.hoon.cashcocoon.domain.Member;
import com.hoon.cashcocoon.domain.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberPortIn {

    private final MemberEntityRepository memberEntityRepository;

    @Override
    @Transactional
    public Member registerMember(MemberRequest memberRequest) {
        if (memberEntityRepository.existsByEmail(memberRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        Member member = Member.builder()
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .build();
        MemberEntity memberEntity = MemberMapper.toEntity(member);
        MemberEntity saved = memberEntityRepository.save(memberEntity);
        return MemberMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Member loginMember(MemberRequest memberRequest) {
        MemberEntity member = memberEntityRepository.findByEmail(memberRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("없는 계정입니다." + memberRequest.getEmail()));
        if (!new BCryptPasswordEncoder().matches(memberRequest.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("None Email.");
        }
        return MemberMapper.toDomain(member);
    }

    @Override
    public Member resetPassword(MemberRequest memberRequest) {
        return null;
    }
}