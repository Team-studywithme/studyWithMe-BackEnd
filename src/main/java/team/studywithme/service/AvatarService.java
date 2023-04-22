package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AvatarRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public Avatar saveGiveNickname(String nickname) {
        Avatar avatar = new Avatar(nickname);
        return avatarRepository.save(avatar);
    }

    public Avatar saveGiveDeActiveAvatar(Avatar avatar) {
        avatar.onActive();
        // create At 처리로직

        return avatarRepository.save(avatar);
    }

    public Avatar update(Long avatarID, String nickname){
        Optional<Avatar> avatarOptional = avatarRepository.findById(avatarID);

        if(avatarOptional.isEmpty()){
            return null;
        }

        Avatar avatar = avatarOptional.get();
        avatar.setNickname(nickname);
        return avatarRepository.save(avatar);
    }

    public void delete(Long avatarID){
        Optional<Avatar> avatarOptional = avatarRepository.findById(avatarID);

        if(avatarOptional.isEmpty()){
            return;
        }

        Avatar avatar = avatarOptional.get();
        avatar.deActive();
        avatarRepository.save(avatar);
    }
}
