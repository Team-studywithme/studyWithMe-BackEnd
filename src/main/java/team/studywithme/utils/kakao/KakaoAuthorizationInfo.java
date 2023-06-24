package team.studywithme.utils.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoAuthorizationInfo {

    @Value("${spring.kakao.id}")
    private String clientId;

    private final String redirectUri = "https://studywithmedeveloper.shop/oauth/callback/kakao";

    private final String authorizationGrantType = "authorization_code";

    private String tokenUri = "https://kauth.kakao.com/oauth/token";

    private String userInfoUri = "https://kapi.kakao.com/v2/user/me";

    private String token_remove_uri = "https://kapi.kakao.com/v1/user/logout";

    public static String SUCCESS_KAKAO = "success_kakao";

    public static String JSON_PROCESSING_EXCEOTION = "json_processing_exception";

    public static String USERINFO_EXCEPTION = "user_info_exception";

    public static String EXPIRE_EXCEPTION = "expire_exception";

    public static String ACCESS_TOKEN_EXCEPTION = "access_token_exception";
}
