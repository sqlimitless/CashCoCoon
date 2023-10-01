package com.hoon.cashcocoon.application.dto;

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
}
