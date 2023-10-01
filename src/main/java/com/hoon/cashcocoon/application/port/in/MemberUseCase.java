package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.adapter.in.member.request.MemberRequest;

public interface MemberUseCase {
    MemberDto registerMember(MemberRequest memberRequest);

    MemberDto loginMember(MemberRequest memberRequest);

    void resetPassword(MemberRequest memberRequest);
}
