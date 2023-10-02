package com.hoon.cashcocoon.adapter.in.member.response;

import com.hoon.cashcocoon.application.dto.MemberDto;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberResponse {
    private long idx;
    private String email;

    public static MemberResponse of(MemberDto memberDto) {
        return MemberResponse.builder()
                .idx(memberDto.getIdx())
                .email(memberDto.getEmail())
                .build();
    }
}
