package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.AvatarRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;

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

    public void delete(Long avatarID){
        Avatar avatar = avatarRepository.findAvatarById(avatarID);

        avatar.deActive();
    }

    public HashMap<Long, String> findByPostList(List<Post> postList){
        Set<Long> idSet = postList.stream().map(post -> post.getAvatar().getId()).collect(Collectors.toSet());
        List<Avatar> avatarList = avatarRepository.findByIdList(idSet);

        return ListToHashMapForNickname(avatarList);
    }

    public HashMap<Long, String> ListToHashMapForNickname(List<Avatar> avatarList){
        HashMap<Long, String> hashMap = new HashMap<>();

        for(Avatar avatar : avatarList){
            hashMap.put(avatar.getId(), avatar.getNickname());
        }

        return hashMap;
    }
}
