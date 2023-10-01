package com.hoon.cashcocoon.domain.member.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PasswordChangedEvent {
    private String toAddress;
    private String newPassword;
}
