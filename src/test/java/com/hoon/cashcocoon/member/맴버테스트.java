package com.hoon.cashcocoon.member;

import com.hoon.cashcocoon.adapter.in.request.MemberRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class 맴버테스트 {

    @Test
    void 비밀번호벨리데이션체크(){
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setPassword("1q!Q");
        assertFalse(memberRequest.checkPasswordPattern());
        memberRequest.setPassword("QWER1234");
        assertFalse(memberRequest.checkPasswordPattern());
        memberRequest.setPassword("1234qwer!@#$QWER");
        assertTrue(memberRequest.checkPasswordPattern());
    }
}
