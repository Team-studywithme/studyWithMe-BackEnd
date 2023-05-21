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
import team.studywithme.utils.profanity.KoreanProfanityFilter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final KoreanProfanityFilter koreanProfanityFilter;

    @Transactional
    public Comment createComment(CommentRequest commentRequest, Long avatarID){
        Comment comment = new Comment(
                new Avatar(avatarID), new Post(commentRequest.getPost_id()),
                koreanProfanityFilter.filterProfanity(commentRequest.getContent()));

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(UpdateCommentRequest updateCommentRequest, Long avatarID){
        Comment comment = commentRepository.findCommentById(updateCommentRequest.getComment_id());

        if(comment == null){
            throw new IllegalArgumentException("존재하지않는 댓글 PK가 요청되었습니다.");
        }
        else if(!comment.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");
        }

        updateCommentRequest.setContent(koreanProfanityFilter.filterProfanity(updateCommentRequest.getContent()));
        comment.updateComment(updateCommentRequest);
        return comment;
    }

    @Transactional
    public void deleteComment(Long commentID, Long avatarID){
        Comment comment = commentRepository.findCommentById(commentID);

        if(comment == null){
            throw new IllegalArgumentException("존재하지않는 댓글 PK가 요청되었습니다.");
        }
        else if(!comment.getAvatar().getId().equals(avatarID)){
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");
        }

        comment.deActive();
    }
}
