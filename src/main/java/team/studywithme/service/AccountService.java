package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.utils.kakao.KakaoLoginUtils;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarService avatarService;
    private final KakaoLoginUtils kakaoLoginUtils;

    @Transactional
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

        return avatar.getId();
    }

    @Transactional
    public void delete(Long avatarID){
        avatarService.delete(avatarID);

        Account account = accountRepository.findAccountByAvatarID(avatarID);

        account.deActive();
    }
}
