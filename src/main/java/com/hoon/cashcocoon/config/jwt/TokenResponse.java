package com.hoon.cashcocoon.config.jwt;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String accessToken;
    private long accessTokenExpireIn;

    @Builder
    public TokenResponse(String accessToken, long accessTokenExpireIn) {
        this.accessToken = accessToken;
        this.accessTokenExpireIn = accessTokenExpireIn;
    }

    public static TokenResponse of(String accessToken, long accessTokenExpireIn) {
        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessTokenExpireIn(accessTokenExpireIn)
                .build();
    }
}
