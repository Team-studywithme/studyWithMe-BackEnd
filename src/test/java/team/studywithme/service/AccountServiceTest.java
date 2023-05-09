package team.studywithme.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.service.structure.UserDataServiceTest;

class AccountServiceTest extends UserDataServiceTest {

    @Autowired
    private AccountService accountService;

    @Nested
    @DisplayName("조건에따른_회원_생성")
    class 조건에따른_회원_생성{

        @Test
        @Transactional
        @DisplayName("서비스에_처음_등록하는_회원일때")
        void 서비스에_처음_등록하는_회원일때() {
            // given
            KakaoUserInfoDto kakaoUserInfo = makeKakaoUserInfo();

            // when
            Avatar result = accountService.createUser(null, kakaoUserInfo);
            Avatar actual = avatarRepository.findAvatarById(result.getId());

            // then
            Assertions.assertEquals(result.getActive(), actual.getActive());
            Assertions.assertEquals(result.getNickname(), actual.getNickname());
        }

        @Test
        @Transactional
        @DisplayName("서비스에_가입된_회원일때")
        void 서비스에_가입된_회원일때() {
            // given
            Avatar avatar = makeAvatar();
            Account account = makeAccount(avatar);

            avatar.deActive();
            account.deActive();

            KakaoUserInfoDto kakaoUserInfo = makeKakaoUserInfo();

            // when
            Avatar result = accountService.createUser(account, kakaoUserInfo);
            Avatar actual = avatarRepository.findAvatarById(result.getId());

            // then
            Assertions.assertEquals(result.getActive(), actual.getActive());
            Assertions.assertEquals(result.getNickname(), actual.getNickname());
        }
    }

    @Nested
    @DisplayName("회원_정보삭제")
    class 회원_정보삭제{

        @Test
        @Transactional
        @DisplayName("회원_정보삭제_성공")
        void 회원_정보삭제_성공(){
            // given
            Avatar avatar = makeAvatar();
            Account account = makeAccount(avatar);

            // when
            accountService.delete(avatar.getId());

            Avatar avatar_actual = avatarRepository.findAvatarByIdNoneIf(avatar.getId());
            Account account_actual = accountRepository.findAccountByIdNoneIf(account.getId());

            // then
            Assertions.assertEquals(0, avatar_actual.getActive());
            Assertions.assertEquals(0, account_actual.getActive());

        }
    }
}