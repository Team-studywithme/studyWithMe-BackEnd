package team.studywithme.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:application-security.yml")
public class KakaoAuthorizationInfo {

    @Value("${registration.client-id}")
    private String clientId;

    @Value("${registration.redirect-uri}")
    private String redirectUri;

    @Value("${registration.authorization-grant-type}")
    private String authorizationGrantType;

    @Value("${provider.token-uri}")
    private String tokenUri;

    @Value("${provider.user-info-uri}")
    private String userInfoUri;

    @Value("${provider.user-logout-token}")
    private String userLogoutToken;
}
