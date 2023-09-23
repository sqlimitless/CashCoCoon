package com.hoon.cashcocoon.application.service;

import com.hoon.cashcocoon.adapter.in.request.MemberRequest;
import com.hoon.cashcocoon.adapter.out.persistance.MemberEntityRepository;
import com.hoon.cashcocoon.adapter.out.persistance.MemberEntity;
import com.hoon.cashcocoon.application.port.in.MemberPortIn;
import com.hoon.cashcocoon.application.port.out.PasswordResetPortOut;
import com.hoon.cashcocoon.domain.Member;
import com.hoon.cashcocoon.domain.MemberMapper;
import com.hoon.cashcocoon.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberPortIn {

    private final MemberEntityRepository memberEntityRepository;
    private final PasswordResetPortOut passwordResetPortOut;

    @Override
    @Transactional
    public Member registerMember(MemberRequest memberRequest) {
        if (memberEntityRepository.existsByEmail(memberRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        Member member = Member.builder()
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .name(memberRequest.getName())
                .role(Role.USER)
                .build();
        MemberEntity memberEntity = MemberMapper.toEntity(member);
        MemberEntity saved = memberEntityRepository.save(memberEntity);
        return MemberMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Member loginMember(MemberRequest memberRequest) {
        MemberEntity member = memberEntityRepository.findByEmail(memberRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + memberRequest.getEmail()));
        if (!new BCryptPasswordEncoder().matches(memberRequest.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("None Email.");
        }
        return MemberMapper.toDomain(member);
    }

    @Override
    @Transactional
    public void resetPassword(MemberRequest memberRequest) {
        MemberEntity member = memberEntityRepository.findByEmail(memberRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("None Email" + memberRequest.getEmail()));

        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String NUMBERS = "0123456789";
        final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/";
        final String ALL_CHARACTERS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;

        Random random = new Random();
        List<Character> password = Arrays.asList(
                UPPER_CASE_LETTERS.charAt(random.nextInt(UPPER_CASE_LETTERS.length())),
                LOWER_CASE_LETTERS.charAt(random.nextInt(LOWER_CASE_LETTERS.length())),
                NUMBERS.charAt(random.nextInt(NUMBERS.length())),
                SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length()))
        );

        for (int i = 4; i < 8; i++) {
            password.add(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        Collections.shuffle(password);
        StringBuilder passwordStr = new StringBuilder();
        for (char c : password) {
            passwordStr.append(c);
        }
        member.updatePassword(passwordStr.toString());
        passwordResetPortOut.passwordReset(member.getEmail(), passwordStr.toString());
    }
}