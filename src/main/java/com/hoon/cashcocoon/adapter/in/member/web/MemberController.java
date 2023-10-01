package com.hoon.cashcocoon.adapter.in.member.web;

import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.adapter.in.member.request.MemberRequest;
import com.hoon.cashcocoon.adapter.in.member.response.MemberResponse;
import com.hoon.cashcocoon.application.port.in.MemberUseCase;
import com.hoon.cashcocoon.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberRequest memberRequest) {
        try {
            if (!memberRequest.checkEmailPattern()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email pattern.");
            }
            if (!memberRequest.checkPasswordPattern()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password pattern.");
            }
            MemberDto member = memberUseCase.registerMember(memberRequest);
            return ResponseEntity.ok(MemberResponse.fromEntity(member));
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Registration failed: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberRequest memberRequest) {
        try {
            MemberDto member = memberUseCase.loginMember(memberRequest);
            return ResponseEntity.ok(tokenProvider.generateJWT(member));
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Registration failed: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody MemberRequest memberRequest) {
        memberUseCase.resetPassword(memberRequest);
        return ResponseEntity.ok(null);
    }
}
