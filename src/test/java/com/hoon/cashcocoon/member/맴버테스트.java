package com.hoon.cashcocoon.member;

import com.hoon.cashcocoon.adapter.in.member.request.ChangePasswordRequest;
import com.hoon.cashcocoon.adapter.in.member.request.RegisterRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaMemberRepository;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.service.MemberService;
import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.domain.member.PasswordConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class 맴버테스트 {

    @InjectMocks
    MemberService memberService;

    @Mock
    JpaMemberRepository jpaMemberRepository;

    @Test
    void 비밀번호벨리데이션체크() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("1q!Q");
        assertFalse(registerRequest.checkPasswordPattern());
        registerRequest.setPassword("QWER1234");
        assertFalse(registerRequest.checkPasswordPattern());
        registerRequest.setPassword("1234qwer!@#$QWER");
        assertTrue(registerRequest.checkPasswordPattern());
    }

    @Test
    void 이메일체크() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("lhjhoon@gmail.com");
        assertTrue(registerRequest.checkEmailPattern());
        registerRequest.setEmail("lhjhoongmail.com");
        assertFalse(registerRequest.checkEmailPattern());
        registerRequest.setEmail("lhjhoon@gmail");
        assertFalse(registerRequest.checkEmailPattern());
    }

    @Test
    void 비밀번호변경() {
        // Given
        PasswordConverter passwordConverter = new PasswordConverter();
        String pw = passwordConverter.convertToEntityAttribute("123!@#qweQWE");
        Member member = Member.builder()
                .email("lhjhoon@gmail.com")
                .password(pw)
                .name("이훈재")
                .idx(1)
                .build();
        when(jpaMemberRepository.findByEmail("lhjhoon@gmail.com")).thenReturn(Optional.of(member));
        // When
        String newPass = "qweQWE123!@#";
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setPassword(pw);
        changePasswordRequest.setNewPassword(newPass);

        MemberDto memberDto = memberService.changePassword(1L, changePasswordRequest);
        // Then
        assertEquals(memberDto.getPassword(), passwordConverter.convertToEntityAttribute(newPass));
    }
}
