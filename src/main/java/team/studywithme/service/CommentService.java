package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.CommentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Slice<Comment> findCommentSliceListByPageable(Pageable pageable, Post post){

        return commentRepository.findSliceComments(pageable, post.getId());
    }

    @Transactional
    public void deleteCommentByPost(Post post){
        int result = commentRepository.deleteByPost(post.getId());
    }

    @Transactional
    public void createComment(CommentRequest commentRequest, Long avatarID){
        Comment comment = new Comment(
                new Avatar(avatarID), new Post(commentRequest.getPost_id()), commentRequest.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest updateCommentRequest, Long avatarID){
        Comment comment = commentRepository.findCommentById(updateCommentRequest.getComment_id());

        comment.setContent(updateCommentRequest.getContent());
    }

    @Transactional
    public void deleteComment(Long commentID, Long avatarID){
        Comment comment = commentRepository.findCommentById(commentID);

        comment.deActive();
    }
}
