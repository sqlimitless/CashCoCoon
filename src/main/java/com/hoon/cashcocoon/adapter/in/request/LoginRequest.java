package com.hoon.cashcocoon.adapter.in.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginRequest {

    private String email;
    private String password;
}
