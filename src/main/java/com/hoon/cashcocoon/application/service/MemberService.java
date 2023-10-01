package com.hoon.cashcocoon.application.service;

import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.adapter.in.member.request.MemberRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaMemberRepository;
import com.hoon.cashcocoon.application.mapper.MemberMapper;
import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.application.port.in.MemberUseCase;
import com.hoon.cashcocoon.application.port.out.PasswordResetPortOut;
import com.hoon.cashcocoon.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final JpaMemberRepository memberEntityRepository;
    private final PasswordResetPortOut passwordResetPortOut;

    @Override
    @Transactional
    public MemberDto registerMember(MemberRequest memberRequest) {
        if (memberEntityRepository.existsByEmail(memberRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        MemberDto memberDto = MemberDto.builder()
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .name(memberRequest.getName())
                .roles(Set.of(Role.USER))
                .build();
        Member memberEntity = MemberMapper.toEntity(memberDto);
        Member saved = memberEntityRepository.save(memberEntity);
        return MemberMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto loginMember(MemberRequest memberRequest) {
        Member member = memberEntityRepository.findByEmail(memberRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + memberRequest.getEmail()));
        if (!new BCryptPasswordEncoder().matches(memberRequest.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("None Email.");
        }
        return MemberMapper.toDto(member);
    }

    @Override
    @Transactional
    public void resetPassword(MemberRequest memberRequest) {
        Member member = memberEntityRepository.findByEmail(memberRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + memberRequest.getEmail()));
        member.updatePassword();
        passwordResetPortOut.passwordReset(member);
    }
}