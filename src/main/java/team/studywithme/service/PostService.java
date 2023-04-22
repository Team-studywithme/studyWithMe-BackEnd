package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AvatarService avatarService;

    public List<PostResponse> findPostResponseListByPageable(Pageable pageable, Long boardID){
        List<Post> postList = postRepository.findPagePosts(pageable, boardID).getContent();
        HashMap<Long, String> avatarMap =  avatarService.findByPostList(postList);

        return postList.stream().map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getCreatedDate(),
                avatarMap.get(post.getAvatar().getId()))).collect(Collectors.toList());
    }
}
