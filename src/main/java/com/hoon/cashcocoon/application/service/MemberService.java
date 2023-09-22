package com.hoon.cashcocoon.application.service;

import com.hoon.cashcocoon.adapter.out.MemberEntityRepository;
import com.hoon.cashcocoon.application.port.in.MemberPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberPortIn, UserDetailsService {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberEntityRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 계정입니다." + email));
    }
}