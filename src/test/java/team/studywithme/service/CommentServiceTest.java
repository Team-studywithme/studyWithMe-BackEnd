package team.studywithme.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.service.structure.PostDataServiceTest;

import java.util.List;

public class CommentServiceTest extends PostDataServiceTest {

    @Autowired
    private CommentService commentService;

    @Nested
    @DisplayName("댓글_생성")
    class 댓글_생성 {

        @Test
        @Transactional
        @DisplayName("댓글_생성")
        void 댓글_생성() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            CommentRequest commentRequest = new CommentRequest(post.getId(), "example_content_test");

            // when
            commentService.createComment(commentRequest, avatar.getId());
        }
    }

    @Nested
    @DisplayName("댓글_변경")
    class 댓글_변경 {

        @Test
        @Transactional
        @DisplayName("댓글_생성")
        void 댓글_생성() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeCommentList(avatar, post);

            UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest(commentList.get(0).getId(), "update_content_test");

            // when
            commentService.updateComment(updateCommentRequest, avatar.getId());
        }
    }

    @Nested
    @DisplayName("댓글_삭제")
    class 댓글_삭제 {

        @Test
        @Transactional
        @DisplayName("댓글_삭제")
        void 댓글_삭제() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeCommentList(avatar, post);

            // when
            commentService.deleteComment(commentList.get(0).getId(), avatar.getId());
        }
    }
}
