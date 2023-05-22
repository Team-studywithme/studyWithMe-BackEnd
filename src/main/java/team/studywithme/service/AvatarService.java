package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AvatarRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @Transactional
    public int update(Long avatarID, String nickname){
        Avatar avatar = avatarRepository.findAvatarById(avatarID);
        if(avatar == null){
            throw new IllegalArgumentException("올바른 사용자 PK가 아닙니다.");
        }

        return avatarRepository.updateNickname(avatarID, nickname);
    }
}
