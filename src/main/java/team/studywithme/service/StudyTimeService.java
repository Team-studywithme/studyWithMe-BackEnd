package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.StudyingAvatarListResponse;
import team.studywithme.api.controller.dto.response.StudyingUserResponse;
import team.studywithme.domain.entity.StudyTime;
import team.studywithme.repository.StudyTimeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyTimeService {

    private final StudyTimeRepository studyTimeRepository;

    public StudyingAvatarListResponse studyingAvatarList() {
        List<StudyTime> studyTimeList = studyTimeRepository.findStudyTimeByStudyDate(LocalDate.now());

        List<StudyingUserResponse> list = studyTimeList.stream()
                .map(studyTime
                        -> new StudyingUserResponse(studyTime.getAvatar().getNickname(), studyTime.getStudyTime()))
                .collect(Collectors.toList());

        return new StudyingAvatarListResponse(list.size(), list);
    }

}
