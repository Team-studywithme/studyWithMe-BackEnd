package team.studywithme.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StudyingAvatarListResponse {


    private int count;

    private List<StudyingUserResponse> studyingUserResponses;

    public StudyingAvatarListResponse(int count, List<StudyingUserResponse> studyingUserResponses) {
        this.count = count;
        this.studyingUserResponses = studyingUserResponses;
    }
}
