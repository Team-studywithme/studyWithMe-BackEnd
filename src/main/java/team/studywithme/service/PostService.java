package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.api.controller.dto.response.CommentDetailResponse;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.AvatarRepository;
import team.studywithme.repository.CommentRepository;
import team.studywithme.repository.PostRepository;
import team.studywithme.utils.profanity.KoreanProfanityFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final int comment_size = 5;

    private final AvatarRepository avatarRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final KoreanProfanityFilter koreanProfanityFilter;

    @Transactional
    public PostDetailResponse detailPost(int page, Long postID){
        Post post = postRepository.findPostById(postID);
        post.upHits();

        Avatar avatar = avatarRepository.findAvatarById(post.getAvatar().getId());

        Slice<Comment> commentSlice = commentRepository.findSliceComments(PageRequest.of(page, comment_size), post.getId());
        List<Comment> commentList = commentSlice.getContent();

        Set<Long> idSet = commentList.stream().map(comment -> comment.getAvatar().getId()).collect(Collectors.toSet());
        List<Avatar> avatarList = avatarRepository.findByIdList(idSet);

        HashMap<Long, String> avatarMap = ListToHashMapForNickname(avatarList);

        List<CommentDetailResponse> commentDetailResponseList = commentSlice.getContent().stream().map(
                comment -> new CommentDetailResponse(comment.getId(),
                comment.getContent(),
                comment.getAvatar().getId(),
                avatarMap.get(comment.getAvatar().getId()))).collect(Collectors.toList());

        return new PostDetailResponse().postCommentToPostDetailResponse(post, avatar, commentDetailResponseList, commentSlice.hasNext());
    }

    @Transactional
    public Post createPost(PostRequest postRequest, Long avatarID){
        Post post = new Post(
                new Avatar(avatarID),
                new Board(postRequest.getBoard_id()),
                0,
                koreanProfanityFilter.filterProfanity(postRequest.getTitle()),
                koreanProfanityFilter.filterProfanity(postRequest.getContent()));

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(UpdatePostRequest updatePostRequest, Long avatarID){
        Post post = postRepository.findPostById(updatePostRequest.getPost_id());

        if(post == null){
            throw new IllegalArgumentException("존재하지않는 게시물 PK가 요청되었습니다.");
        }
        if(!post.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("게시물의 작성자가 아닙니다.");
        }

        updatePostRequest.setElement(
                koreanProfanityFilter.filterProfanity(updatePostRequest.getTitle()),
                koreanProfanityFilter.filterProfanity(updatePostRequest.getContent()));

        post.updatePost(updatePostRequest);
        return post;
    }

    @Transactional
    public void deletePost(Long postID, Long avatarID){
        Post post = postRepository.findPostById(postID);

        if(post == null){
            throw new IllegalArgumentException("존재하지않는 게시물 PK가 요청되었습니다.");
        }
        if(!post.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("게시물의 작성자가 아닙니다.");
        }

        post.deActive();

        commentRepository.deleteByPost(post.getId());
    }

    public HashMap<Long, String> ListToHashMapForNickname(List<Avatar> avatarList){
        HashMap<Long, String> hashMap = new HashMap<>();

        for(Avatar avatar : avatarList){
            hashMap.put(avatar.getId(), avatar.getNickname());
        }

        return hashMap;
    }
}
