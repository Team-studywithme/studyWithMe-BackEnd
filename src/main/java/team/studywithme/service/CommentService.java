package team.studywithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.response.CommentDetailResponse;
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
}
