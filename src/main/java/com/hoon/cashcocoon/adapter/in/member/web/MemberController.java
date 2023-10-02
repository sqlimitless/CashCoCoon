package com.hoon.cashcocoon.adapter.in.member.web;

import com.hoon.cashcocoon.adapter.in.member.request.ChangePasswordRequest;
import com.hoon.cashcocoon.adapter.in.member.request.LoginRequest;
import com.hoon.cashcocoon.adapter.in.member.request.RegisterRequest;
import com.hoon.cashcocoon.adapter.in.member.request.ResetRequest;
import com.hoon.cashcocoon.adapter.in.member.response.MemberResponse;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.port.in.MemberUseCase;
import com.hoon.cashcocoon.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody RegisterRequest registerRequest) {
        try {
            if (!registerRequest.checkEmailPattern()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email pattern.");
            }
            if (!registerRequest.checkPasswordPattern()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password pattern.");
            }
            MemberDto memberDto = memberUseCase.registerMember(registerRequest);
            return ResponseEntity.ok(MemberResponse.of(memberDto));
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Registration failed: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequest loginRequest) {
        try {
            MemberDto member = memberUseCase.loginMember(loginRequest);
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
    public ResponseEntity<?> resetPassword(@RequestBody ResetRequest resetRequest) {
        MemberDto memberDto = memberUseCase.resetPassword(resetRequest);
        return ResponseEntity.ok(MemberResponse.of(memberDto));
    }

    @PatchMapping("/{idx}/new-password")
    public ResponseEntity<?> changePassword(@PathVariable("idx")Long idx, @RequestBody ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.checkPasswordPattern()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid newPassword pattern.");
        }
        MemberDto memberDto;
        try{
            memberDto = memberUseCase.changePassword(idx, changePasswordRequest);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(MemberResponse.of(memberDto));
    }
}
