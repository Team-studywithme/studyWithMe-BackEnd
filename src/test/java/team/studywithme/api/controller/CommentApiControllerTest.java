package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentApiControllerTest extends PostDataTest {

    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("댓글_생성")
    class 댓글_생성{

        @Test
        @Transactional
        @DisplayName("댓글_생성_성공")
        void 댓글_생성_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            Comment comment = makeComment(avatar, post);

            session.setAttribute("session", avatar.getId());
            String url = "/comment";
            String content = objectMapper.writeValueAsString(new CommentRequest(
                    post.getId(), comment.getContent()
            ));

            // when && then
            mockMvc.perform(post(url)
                            .session(session)
                            .content(content))
                    .andDo(print())
                    .andDo(document("comment-post",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("댓글_수정")
    class 댓글_수정{

        @Test
        @Transactional
        @DisplayName("댓글_수정_성공")
        void 댓글_수정_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            Comment comment = makeComment(avatar, post);

            session.setAttribute("session", avatar.getId());
            String url = "/comment";
            String content = objectMapper.writeValueAsString(new UpdateCommentRequest(
                    comment.getId(), comment.getContent()
            ));

            // when && then
            mockMvc.perform(patch(url)
                            .session(session)
                            .content(content))
                    .andDo(print())
                    .andDo(document("comment-patch",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("댓글_삭제")
    class 댓글_삭제{

        @Test
        @Transactional
        @DisplayName("댓글_삭제_성공")
        void 댓글_삭제_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            Comment comment = makeComment(avatar, post);

            session.setAttribute("session", avatar.getId());
            String url = "/comment?commentID=" + comment.getId();

            // when && then
            mockMvc.perform(delete(url)
                            .session(session))
                    .andDo(print())
                    .andDo(document("comment-delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}