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
            return 0;
        }

        return avatarRepository.updateNickname(avatarID, nickname);
    }

    public Avatar findByPost(Post post){
        Long avatarID = post.getAvatar().getId();

        return avatarRepository.findAvatarById(avatarID);
    }

    public HashMap<Long, String> findByPostList(List<Post> postList){
        Set<Long> idSet = postList.stream().map(post -> post.getAvatar().getId()).collect(Collectors.toSet());
        List<Avatar> avatarList = avatarRepository.findByIdList(idSet);

        return ListToHashMapForNickname(avatarList);
    }

    public HashMap<Long, String> findByCommentList(List<Comment> commentList){
        Set<Long> idSet = commentList.stream().map(comment -> comment.getAvatar().getId()).collect(Collectors.toSet());
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
