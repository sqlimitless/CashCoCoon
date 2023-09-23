package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.request.MemberRequest;
import com.hoon.cashcocoon.domain.Member;

public interface MemberPortIn {
    Member registerMember(MemberRequest memberRequest);

    Member loginMember(MemberRequest memberRequest);

    Member resetPassword(MemberRequest memberRequest);
}
