package team.studywithme.utils.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySources({
        @PropertySource(name = "kakaoinfo" ,value = "classpath:kakaosecuritykey.yml")
})
public class KakaoAuthorizationInfo {

    @Value("${spring.kakao.id}")
    private String clientId;

    @Value("${registration.redirect-uri}")
    private String redirectUri;

    @Value("${registration.authorization-grant-type}")
    private String authorizationGrantType;

    @Value("${provider.token-uri}")
    private String tokenUri;

    @Value("${provider.user-info-uri}")
    private String userInfoUri;

}
