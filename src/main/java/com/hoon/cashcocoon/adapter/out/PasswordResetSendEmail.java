package com.hoon.cashcocoon.adapter.out;

import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.application.port.out.PasswordResetPortOut;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetSendEmail implements PasswordResetPortOut {
    @Override
    public void passwordReset(Member member) {

    }
}
