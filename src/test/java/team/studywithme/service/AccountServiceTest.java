package team.studywithme.service;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.UserResponse;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.structure.UserDataTest;
import team.studywithme.utils.kakao.KakaoAuthorizationInfo;
import team.studywithme.utils.kakao.MockKakaoLoginUtilsImpl;


@Import(MockKakaoLoginUtilsImpl.class)
class AccountServiceTest extends UserDataTest {

    @Autowired
    private AccountService accountService;

    @AfterEach
    public void afterSetup(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[Service][Account] 카카오 로그인")
    class 카카오_로그인{

        @Test
        @DisplayName("[Service][Account] 카카오 로그인 성공 테스트 - 서비스에 처음 등록하는 회원일 때")
        void 카카오_로그인_성공_테스트_서비스에_처음_등록하는_회원일_때() {
            // when
            Long actual = accountService.kakaoLogin(KakaoAuthorizationInfo.SUCCESS_KAKAO);

            // then
            Assertions.assertNotNull(actual);
        }

        @Test
        @Transactional
        @DisplayName("[Service][Account] 카카오 로그인 성공 테스트 - 서비스에 가입된 회원일 때")
        void 카카오_로그인_성공_테스트_서비스에_가입된_회원일_때() {
            // given
            Avatar avatar = makeAvatar();
            Account account = makeKakaoAccount(avatar);

            Long expect = avatar.getId();

            // when
            Long actual = accountService.kakaoLogin(KakaoAuthorizationInfo.SUCCESS_KAKAO);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Service][Account] 카카오 로그인 실패 테스트 - USERINFO EXCEPTION일 때")
        void 카카오_로그인_실패_테스트_USERINFO_EXCEPTION() {
            // then
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                accountService.kakaoLogin(KakaoAuthorizationInfo.USERINFO_EXCEPTION);
            });
        }

        @Test
        @DisplayName("[Service][Account] 카카오 로그인 실패 테스트 - ACCESS TOKEN EXCEPTION일 때")
        void 카카오_로그인_실패_테스트_ACCESS_TOKEN_EXCEPTION() {
            // then
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                accountService.kakaoLogin(KakaoAuthorizationInfo.ACCESS_TOKEN_EXCEPTION);
            });
        }

        @Test
        @DisplayName("[Service][Account] 카카오 로그인 실패 테스트 - EXPIRE EXCEPTION일 때")
        void 카카오_로그인_실패_테스트_EXPIRE_EXCEPTION() {
            // then
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                accountService.kakaoLogin(KakaoAuthorizationInfo.EXPIRE_EXCEPTION);
            });
        }

        @Test
        @DisplayName("[Service][Account] 카카오 로그인 실패 테스트 - JSON PARSING EXCEPTION일 때")
        void 카카오_로그인_실패_테스트_JSON_PARSING_EXCEPTION() {
            // then
            Assertions.assertThrows(RuntimeException.class, () -> {
                accountService.kakaoLogin(KakaoAuthorizationInfo.JSON_PROCESSING_EXCEOTION);
            });
        }
    }

    @Nested
    @DisplayName("[Service][Account] 회원 정보조회")
    class 회원_정보조회{

        @Test
        @DisplayName("[Service][Account] 회원 정보조회 성공 테스트")
        void 회원_정보조회_성공_테스트(){
            // given
            Avatar avatar = makeAvatar();
            Account account = makeAccount(avatar);

            // when
            UserResponse actual = accountService.get(avatar.getId());

            UserResponse expect = new UserResponse(account.getEmail(), avatar.getNickname());

            // then
            Assertions.assertEquals(expect, actual);

        }
    }

    @Nested
    @DisplayName("[Service][Account] 회원 정보삭제")
    class 회원_정보삭제{

        @Test
        @DisplayName("[Service][Account] 회원 정보삭제 성공 테스트")
        void 회원_정보삭제_성공_테스트(){
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