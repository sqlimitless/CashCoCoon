package com.hoon.cashcocoon.adapter.in.web;

import com.hoon.cashcocoon.adapter.in.request.LoginRequest;
import com.hoon.cashcocoon.application.port.in.MemberPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberPortIn memberPortIn;

    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody LoginRequest loginRequest) {
        System.out.println("MemberController.registerUser");
        System.out.println("loginRequest = " + loginRequest);
        memberPortIn.registerMember(loginRequest);
        return ResponseEntity.ok(null);
    }
}
