package team.studywithme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.domain.entity.*;
import team.studywithme.structure.UserDataTest;

class AvatarServiceTest extends UserDataTest {

    @Autowired
    private AvatarService avatarService;

    @Nested
    @DisplayName("닉네임_변경")
    class 닉네임_변경 {

        @Test
        @Transactional
        @DisplayName("닉네임_변경_avatarID에_맞는_사용자가_DB에_존재할때")
        void 닉네임_변경_avatarID에_맞는_사용자가_DB에_존재할때() {
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