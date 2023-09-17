package com.hoon.cashcocoon.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private String email;
    private String password;
}
