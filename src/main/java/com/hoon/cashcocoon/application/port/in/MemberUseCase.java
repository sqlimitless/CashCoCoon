package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.member.request.*;
import com.hoon.cashcocoon.application.dto.MemberDto;

public interface MemberUseCase {
    MemberDto registerMember(RegisterRequest registerRequest);

    MemberDto loginMember(LoginRequest loginRequest);

    MemberDto resetPassword(ResetRequest memberRequest);

    MemberDto changePassword(Long idx, ChangePasswordRequest changePasswordRequest);
}
