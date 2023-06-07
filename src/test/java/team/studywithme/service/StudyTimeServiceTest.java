package team.studywithme.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import team.studywithme.api.controller.dto.response.StudyingAvatarListResponse;
import team.studywithme.api.controller.dto.response.StudyingUserResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.StudyTime;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.StudyTimeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
@SpringBootTest
class StudyTimeServiceTest {

    @Autowired
    StudyTimeService studyTimeService;

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    StudyTimeRepository studyTimeRepository;

    Avatar avatar1;
    Avatar avatar2;

    @BeforeEach
    void init() {
        avatar1 = new Avatar("test1");
        avatarRepository.save(avatar1);

        avatar2 = new Avatar("test2");
        avatarRepository.save(avatar1);
    }

    @AfterEach
    void destroy() {
        studyTimeRepository.deleteAll();
        avatarRepository.deleteAll();
    }

    @Test
    @DisplayName("오늘자 StudyTime조회시 데이터베이스에 저장된 StudyTime Avatar List를 반환해야 한다.")
    void studyingAvatarList() throws Exception {
        //given
        StudyTime studyTime1 = new StudyTime(avatar1);
        studyTimeRepository.save(studyTime1);
        StudyTime studyTime2 = new StudyTime(avatar1);
        studyTimeRepository.save(studyTime2);
        //when
        StudyingAvatarListResponse studyingAvatarListResponse = studyTimeService.studyingAvatarList();
        //then

        assertThat(studyingAvatarListResponse.getCount()).isEqualTo(2);
    }
}