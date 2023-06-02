package team.studywithme.utils.kakao;

import team.studywithme.api.controller.dto.KakaoUserInfoDto;

public interface KakaoLoginUtils {
    KakaoUserInfoDto getKakaoUserInfo(String code);
}
