package com.hoon.cashcocoon.application.service;

import com.hoon.cashcocoon.adapter.in.member.request.ChangePasswordRequest;
import com.hoon.cashcocoon.adapter.in.member.request.LoginRequest;
import com.hoon.cashcocoon.adapter.in.member.request.RegisterRequest;
import com.hoon.cashcocoon.adapter.in.member.request.ResetRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaMemberRepository;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.port.in.MemberUseCase;
import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.domain.member.Role;
import com.hoon.cashcocoon.domain.member.event.PasswordResetEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final JpaMemberRepository memberEntityRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public MemberDto registerMember(RegisterRequest registerRequest) {
        if (memberEntityRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        MemberDto memberDto = MemberDto.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .name(registerRequest.getName())
                .roles(Set.of(Role.USER))
                .build();
        Member memberEntity = memberDto.toEntity();
        Member saved = memberEntityRepository.save(memberEntity);
        return MemberDto.of(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto loginMember(LoginRequest loginRequest) {
        Member member = memberEntityRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + loginRequest.getEmail()));
        if (!new BCryptPasswordEncoder().matches(loginRequest.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("None Email.");
        }
        return MemberDto.of(member);
    }

    @Override
    @Transactional
    public MemberDto resetPassword(ResetRequest resetRequest) {
        Member member = memberEntityRepository.findByEmail(resetRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + resetRequest.getEmail()));
        member.resetPassword();
        PasswordResetEvent changedEvent = PasswordResetEvent.builder()
                .toAddress(resetRequest.getEmail())
                .newPassword(member.getPassword())
                .build();
        eventPublisher.publishEvent(changedEvent);
        return MemberDto.of(member);
    }

    @Override
    @Transactional
    public MemberDto changePassword(Long idx, ChangePasswordRequest changePasswordRequest) throws IllegalArgumentException{
        Member member = memberEntityRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("None idx" + idx));
        member.changePassword(changePasswordRequest.getPassword(), changePasswordRequest.getNewPassword());
        return MemberDto.of(member);
    }
}