package com.hoon.cashcocoon.adapter.in.request;

import lombok.AccessLevel;
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

    @Getter(AccessLevel.NONE)
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])[A-Za-z0-9!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-]{8,20}$";

    @Getter(AccessLevel.NONE)
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    public boolean checkPasswordPattern() {
        Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }
}
