package com.hoon.cashcocoon.domain;

import com.hoon.cashcocoon.adapter.out.persistance.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberEntity toEntity(Member member) {
        return MemberEntity.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
