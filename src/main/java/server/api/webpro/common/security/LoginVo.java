package server.api.webpro.common.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginVo {
    private String type;
    private String accessToken;
    private Long accessTokenExpired;
    private String refreshToken;
}
