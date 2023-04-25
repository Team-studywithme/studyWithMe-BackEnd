package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.api.controller.dto.response.KakaoLoginResponse;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.utils.kakao.KakaoLoginUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarService avatarService;
    private final KakaoLoginUtils kakaoLoginUtils;

    public Long kakaoLogin(String code){
        KakaoUserInfoDto kakaoUserInfo = kakaoLoginUtils.getKakaoUserInfo(code);

        Account account = accountRepository.findAccountById(kakaoUserInfo.getKakaoServerId());
        Avatar avatar = null;

        // 서비스 등록 회원 처음일때
        if(account == null) {
            avatar = avatarService.saveGiveNickname(kakaoUserInfo.getNickname());

            account = Account.builder()
                    .id(kakaoUserInfo.getKakaoServerId())
                    .email(kakaoUserInfo.getEmail())
                    .avatar(avatar)
                    .build();

            accountRepository.save(account);
        }
        if (account.getActive() == 0) { // 원래 가입 했었을때
            account.onActive();
            accountRepository.save(account);

            avatar = account.getAvatar();
            avatarService.saveGiveDeActiveAvatar(avatar);
        }
        avatar = account.getAvatar();

        return new KakaoLoginResponse().AvatarToKakaoLoginResponse(avatar);
    }
}
