package com.hoon.cashcocoon.adapter.in.member.response;

import com.hoon.cashcocoon.application.dto.MemberDto;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberResponse {
    private String email;

    public static MemberResponse fromEntity(MemberDto memberDto) {
        return MemberResponse.builder()
                .email(memberDto.getEmail())
                .build();
    }
}
