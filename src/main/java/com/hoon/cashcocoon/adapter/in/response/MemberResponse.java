package com.hoon.cashcocoon.adapter.in.response;

import com.hoon.cashcocoon.domain.Member;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberResponse {
    private String email;

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .build();
    }
}
