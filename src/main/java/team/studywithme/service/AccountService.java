package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.api.controller.dto.response.KakaoLoginResponse;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;
import team.studywithme.utils.KakaoLoginUtils;

import javax.transaction.Transactional;

@Slf4j
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
        log.info("kakaoUserInfo serverId : {} ",kakaoUserInfo.getKakaoServerId());

        Avatar avatar = null;

        /** Account 테이블의 기본키를 kakaoServerID로 했으므로
         * 유저 삭제될 때 active = 0 으로 설정만 하게되면
         * 추후 회원가입 할때 기본키가 충돌되서 save가 아닌 update 쿼리가 발생
         * 그럼 createdAt과 modifiedAt, active까지 수정이 안돼
         *
         * 따라서 if / else-if / else 로 구분짓고 (그러기위해 and active = 1 조건 삭제)
         * createdAt과 modifiedAt는 강제로 now()로 맞추고
         * active는 onActive해서 업데이트 함 */

        // 서비스 등록 회원 처음일때
        if(account == null) {
            avatar = avatarService.saveGiveNickname(kakaoUserInfo.getNickname());

            account = Account.builder()
                    .id(kakaoUserInfo.getKakaoServerId())
                    .email(kakaoUserInfo.getEmail())
                    .avatar(avatar).build();

            accountRepository.save(account);
        } else if (account.getActive() == 0) { // 원래 가입 했었을때
            account.onActive();
            accountRepository.save(account);

            avatar = account.getAvatar();
            avatarService.saveGiveDeActiveAvatar(avatar);
        } else{
            avatar = account.getAvatar();
        }

        return new KakaoLoginResponse().AvatarToKakaoLoginResponse(avatar);
    }
}
