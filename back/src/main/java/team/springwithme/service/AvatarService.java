package team.springwithme.service;

import team.springwithme.domain.dto.StudyDto;
import team.springwithme.domain.entity.Account;
import team.springwithme.domain.entity.Avatar;

public interface AvatarService {
    StudyDto verificationKakao(String code);

    Avatar save(Avatar avatar);
}
