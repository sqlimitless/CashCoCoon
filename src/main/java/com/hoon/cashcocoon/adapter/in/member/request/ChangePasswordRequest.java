package com.hoon.cashcocoon.adapter.in.member.request;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class ChangePasswordRequest {

    private String password;
    private String newPassword;

    public boolean checkPasswordPattern() {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])(?=.*[!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-])[A-Za-z0-9!@#$%^&*()_+\\[\\]{}|;:,.<>?`~\"'=-]{8,20}$";
        Matcher matcher = Pattern.compile(PASSWORD_PATTERN).matcher(newPassword);
        return matcher.matches();
    }
}
