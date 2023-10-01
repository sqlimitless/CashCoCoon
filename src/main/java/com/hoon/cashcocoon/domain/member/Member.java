package com.hoon.cashcocoon.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Table(name = "member")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_idx"))
    private Set<Role> roles = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void updatePassword(){
        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String NUMBERS = "0123456789";
        final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/";
        final String ALL_CHARACTERS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;

        Random random = new Random();
        List<Character> password = Arrays.asList(
                UPPER_CASE_LETTERS.charAt(random.nextInt(UPPER_CASE_LETTERS.length())),
                LOWER_CASE_LETTERS.charAt(random.nextInt(LOWER_CASE_LETTERS.length())),
                NUMBERS.charAt(random.nextInt(NUMBERS.length())),
                SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length()))
        );

        for (int i = 4; i < 8; i++) {
            password.add(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        Collections.shuffle(password);
        StringBuilder passwordStr = new StringBuilder();
        for (char c : password) {
            passwordStr.append(c);
        }
        this.password = passwordStr.toString();
    }
}
