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
    public Avatar saveGiveNickname(String nickname) {
        Avatar avatar = new Avatar(nickname);
        return avatarRepository.save(avatar);
    }

    @Transactional
    public Avatar saveGiveDeActiveAvatar(Avatar avatar) {
        avatar.onActive();
        // create At 처리로직

        return avatarRepository.save(avatar);
    }

    @Transactional
    public int update(Long avatarID, String nickname){
        Avatar avatar = avatarRepository.findAvatarById(avatarID);
        if(avatar == null){
            return 0;
        }

        return avatarRepository.updateNickname(avatarID, nickname);
    }

    @Transactional
    public void delete(Long avatarID){
        Avatar avatar = avatarRepository.findAvatarById(avatarID);

        avatar.deActive();
    }
}
