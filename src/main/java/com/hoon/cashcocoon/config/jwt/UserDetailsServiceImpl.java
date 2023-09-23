package com.hoon.cashcocoon.config.jwt;

import com.hoon.cashcocoon.adapter.out.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberEntityRepository memberEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberEntityRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("None Email" + email));
    }
}
