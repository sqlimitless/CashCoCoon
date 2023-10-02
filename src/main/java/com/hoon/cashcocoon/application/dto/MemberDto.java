package com.hoon.cashcocoon.application.dto;

import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.domain.member.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MemberDto {

    private long idx;
    private String email;
    private String name;
    private String password;
    private Set<Role> roles;

    public Member toEntity() {
        return Member.builder()
                .idx(this.getIdx())
                .email(this.getEmail())
                .name(this.getName())
                .password(this.getPassword())
                .roles(this.getRoles())
                .build();
    }

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .idx(member.getIdx())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .roles(member.getRoles())
                .build();
    }
}
