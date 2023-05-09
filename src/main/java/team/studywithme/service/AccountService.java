package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.utils.kakao.KakaoLoginUtils;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarRepository avatarRepository;
    private final KakaoLoginUtils kakaoLoginUtils;

    @Transactional
    public Long kakaoLogin(String code){
        KakaoUserInfoDto kakaoUserInfo = kakaoLoginUtils.getKakaoUserInfo(code);

        Account account = accountRepository.findAccountByIdNoneIf(kakaoUserInfo.getKakaoServerId());
        Avatar avatar = createUser(account, kakaoUserInfo);

        return avatar.getId();
    }

    public Avatar createUser(Account account, KakaoUserInfoDto kakaoUserInfo){
        Avatar avatar = null;

        // 서비스 등록 회원 처음일때
        if(account == null) {
            avatar = avatarRepository.save(new Avatar(kakaoUserInfo.getNickname()));

            account = Account.builder()
                    .id(kakaoUserInfo.getKakaoServerId())
                    .email(kakaoUserInfo.getEmail())
                    .avatar(avatar)
                    .build();

            accountRepository.save(account);
        }
        else if (account.getActive() == 0) { // 원래 가입 했었을때
            account.onActive();

            avatar = account.getAvatar();
            avatar.onActive();

            // create At 처리로직
        }
        return account.getAvatar();
    }

    @Transactional
    public void delete(Long avatarID){
        Avatar avatar = avatarRepository.findAvatarById(avatarID);
        avatar.deActive();

        Account account = accountRepository.findAccountByAvatarID(avatarID);
        account.deActive();
    }
}
