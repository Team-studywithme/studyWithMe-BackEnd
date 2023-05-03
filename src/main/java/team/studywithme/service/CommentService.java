package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.repository.CommentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentRequest commentRequest, Long avatarID){
        Comment comment = new Comment(
                new Avatar(avatarID), new Post(commentRequest.getPost_id()), commentRequest.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest updateCommentRequest, Long avatarID){
        Comment comment = commentRepository.findCommentById(updateCommentRequest.getComment_id());

        if(!comment.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");
        }

        comment.updateComment(updateCommentRequest);
    }

    @Transactional
    public void deleteComment(Long commentID, Long avatarID){
        Comment comment = commentRepository.findCommentById(commentID);

        if(!comment.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");
        }

        comment.deActive();
    }
}
