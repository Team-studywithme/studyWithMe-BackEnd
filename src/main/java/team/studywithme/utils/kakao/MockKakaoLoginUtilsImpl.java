package team.studywithme.utils.kakao;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;

@Profile("test")
@TestComponent
public class MockKakaoLoginUtilsImpl implements KakaoLoginUtils{

    @Override
    public KakaoUserInfoDto getKakaoUserInfo(String code) {
        if (code.equals(KakaoAuthorizationInfo.SUCCESS_KAKAO)) {
            return new KakaoUserInfoDto(
                    code, "test@gmail.com", "테스트 닉네임"
            );
        }

        if (code.equals(KakaoAuthorizationInfo.USERINFO_EXCEPTION)) {
            throw new IllegalArgumentException("User Info 를 가져오지 못하였습니다." +
                    "\n클라이언트의 인증코드가 틀리거나 이미 사용한 인증코드입니다.");
        }

        if (code.equals(KakaoAuthorizationInfo.ACCESS_TOKEN_EXCEPTION)) {
            throw new IllegalArgumentException("Access Code 를 가져오지 못하였습니다." +
                    "\n클라이언트의 인증코드가 틀리거나 이미 사용한 인증코드입니다." +
                    "\n혹은 grant_type, client_id, redirect_uri 중 잘못이 있을 수 있습니다.");
        }

        if (code.equals(KakaoAuthorizationInfo.EXPIRE_EXCEPTION)) {
            throw new IllegalArgumentException("Access Code 를 만료하지 못하였습니다." +
                    "\n클라이언트의 인증코드가 틀리거나 이미 사용한 인증코드입니다.");
        }

        throw new RuntimeException("Json Processing 에 문제가 발생했습니다.");
    }
}
