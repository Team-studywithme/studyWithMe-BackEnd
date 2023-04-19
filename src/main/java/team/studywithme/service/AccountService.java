package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.api.controller.dto.Response.KakaoLoginResponse;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.utils.KakaoLoginUtils;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarService avatarService;
    private final KakaoLoginUtils kakaoLoginUtils;

    public KakaoLoginResponse kakaoLogin(String code){

        KakaoUserInfoDto kakaoUserInfo = kakaoLoginUtils.getKakaoUserInfo(code);

        Account account = accountRepository.findAccountById(kakaoUserInfo.getKakaoServerId());

        // 서비스 등록 회원이 아니라면 회원가입
        if(account == null) {
            Avatar avatar = avatarService.save(kakaoUserInfo.getNickname());

            account = Account.builder()
                    .id(kakaoUserInfo.getKakaoServerId())
                    .email(kakaoUserInfo.getEmail())
                    .avatar(avatar).build();

            accountRepository.save(account);
        }

        return new KakaoLoginResponse();
    }
}
