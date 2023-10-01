package com.hoon.cashcocoon.application.mapper;

import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.domain.member.Member;

public class MemberMapper {

    public static Member toEntity(MemberDto memberDto){
        return Member.builder()
                .idx(memberDto.getIdx())
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .build();
    }

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .idx(member.getIdx())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .build();
    }
}
