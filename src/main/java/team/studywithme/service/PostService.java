package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.CommentDetailResponse;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.api.controller.dto.response.PostResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AvatarService avatarService;
    private final CommentService commentService;

    public List<PostResponse> findPostResponseListByPageable(Pageable pageable, Long boardID){
        List<Post> postList = postRepository.findPagePosts(pageable, boardID);
        HashMap<Long, String> avatarMap =  avatarService.findByPostList(postList);

        return postList.stream().map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getCreatedDate(),
                avatarMap.get(post.getAvatar().getId()))).collect(Collectors.toList());
    }

    public PostDetailResponse detailPost(Pageable pageable, Long postID){
        Post post = postRepository.findPostById(postID);
        post.upHits();

        Avatar avatar = avatarService.findByPost(post);

        Slice<Comment> commentSlice = commentService.findCommentSliceListByPageable(pageable, post);

        HashMap<Long, String> avatarMap = avatarService.findByCommentList(commentSlice.getContent());

        List<CommentDetailResponse> commentDetailResponseList = commentSlice.getContent().stream().map(
                comment -> new CommentDetailResponse(comment.getId(),
                comment.getContent(),
                avatarMap.get(comment.getAvatar().getId()))).collect(Collectors.toList());

        return new PostDetailResponse().postCommentToPostDetailResponse(post, avatar, commentDetailResponseList, commentSlice.hasNext());
    }
}
