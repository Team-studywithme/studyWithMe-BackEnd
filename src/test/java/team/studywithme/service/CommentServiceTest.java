package team.studywithme.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import java.util.List;

public class CommentServiceTest extends PostDataTest {

    @Autowired
    private CommentService commentService;

    @AfterEach
    public void afterSetup(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[Service][Comment] 댓글 생성")
    class 댓글_생성 {

        @Test
        @DisplayName("[Service][Comment] 댓글 생성 성공 테스트")
        void 댓글_생성_성공_테스트() {
            // given
            String content = "example_content_test";

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            CommentRequest commentRequest = new CommentRequest(post.getId(), content);

            Comment expect = new Comment(avatar, post, content);

            // when
            Comment actual = commentService.createComment(commentRequest, avatar.getId());

            // then
            Assertions.assertEquals(expect.getAvatar().getId(), actual.getAvatar().getId());
            Assertions.assertEquals(expect.getPost().getId(), actual.getPost().getId());
            Assertions.assertEquals(expect.getContent(), actual.getContent());
        }
    }

    @Nested
    @DisplayName("[Service][Comment] 댓글 변경")
    class 댓글_변경 {

        @Test
        @DisplayName("[Service][Comment] 댓글 변경 성공 테스트")
        void 댓글_변경_성공_테스트() {
            // given
            String content = "update_content_test";

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            List<Comment> commentList = makeCommentList(avatar, post);

            UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest(commentList.get(0).getId(), content);

            Comment expect = new Comment(avatar, post, content);

            // when
            Comment actual = commentService.updateComment(updateCommentRequest, avatar.getId());

            // then
            Assertions.assertEquals(expect.getAvatar().getId(), actual.getAvatar().getId());
            Assertions.assertEquals(expect.getPost().getId(), actual.getPost().getId());
            Assertions.assertEquals(expect.getContent(), actual.getContent());
        }
    }

    @Nested
    @DisplayName("[Service][Comment] 댓글 삭제")
    class 댓글_삭제 {

        @Test
        @DisplayName("[Service][Comment] 댓글 삭제 성공 테스트")
        void 댓글_삭제_성공_테스트() {
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            Comment comment = makeComment(avatar, post);

            // when
            commentService.deleteComment(comment.getId(), avatar.getId());

            Comment actual = commentRepository.findCommentById(comment.getId());

            // then
            Assertions.assertNull(actual);
        }
    }
}
