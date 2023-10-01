package com.hoon.cashcocoon.application.port.out;

import com.hoon.cashcocoon.domain.member.Member;

public interface PasswordResetPortOut {
    void passwordReset(Member member);
}
