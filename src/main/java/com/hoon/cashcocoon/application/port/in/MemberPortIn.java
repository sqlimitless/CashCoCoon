package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.request.LoginRequest;

public interface MemberPortIn {
    void registerMember(LoginRequest loginRequest);
}
