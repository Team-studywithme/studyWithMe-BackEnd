package team.studywithme.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.domain.entity.*;
import team.studywithme.structure.UserDataTest;

class AvatarServiceTest extends UserDataTest {

    @Autowired
    private AvatarService avatarService;

    @AfterEach
    public void afterSetup(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[Service][Avatar] 닉네임 변경")
    class 닉네임_변경 {

        @Test
        @DisplayName("[Service][Avatar] 닉네임 변경 성공 테스트 - avatarID에 맞는 사용자가 DB에 존재할때")
        void 닉네임_변경_avatarID에_맞는_사용자가_DB에_존재할때_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            String nickname = "UpdateNickname";

            // when
            int actual = avatarService.update(avatar.getId(), nickname);

            // then
            Assertions.assertTrue(actual >= 1);
        }
    }
}