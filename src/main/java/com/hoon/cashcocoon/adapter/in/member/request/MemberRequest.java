package com.hoon.cashcocoon.adapter.in.member.request;

import com.hoon.cashcocoon.domain.member.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@ToString
public class MemberRequest {

    private String email;
    private String password;
    private String name;
    private Role role;

    public boolean checkEmailPattern() {
        final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(email);
        return matcher.matches();
    }
    public boolean checkPasswordPattern() {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])[A-Za-z0-9!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-]{8,20}$";
        Matcher matcher = Pattern.compile(PASSWORD_PATTERN).matcher(password);
        return matcher.matches();
    }
}
