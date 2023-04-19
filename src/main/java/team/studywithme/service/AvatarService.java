package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AvatarRepository;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public Avatar save(String nickname) {
        Avatar avatar = new Avatar(nickname);
        return avatarRepository.save(avatar);
    }
}
