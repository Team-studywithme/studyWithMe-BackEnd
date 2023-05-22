package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostApiControllerTest extends PostDataTest {

    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("게시물_조회")
    class 게시물_조회{

        @Test
        @Transactional
        @DisplayName("게시물_조회_성공")
        void 게시물_조회_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute("session", avatar.getId());
            String url = "/post?postID=" + post.getId();

            // when && then
            mockMvc.perform(get(url)
                            .session(session))
                    .andDo(print())
                    .andDo(document("post-get",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("게시물_생성")
    class 게시물_생성{

        @Test
        @Transactional
        @DisplayName("게시물_생성_성공")
        void 게시물_생성_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute("session", avatar.getId());
            String url = "/post";
            String content = objectMapper.writeValueAsString(new PostRequest(
                    board.getId(), post.getTitle(), post.getContent()
            ));

            // when && then
            mockMvc.perform(post(url)
                            .session(session)
                            .content(content))
                    .andDo(print())
                    .andDo(document("post-post",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("게시물_변경")
    class 게시물_변경{

        @Test
        @Transactional
        @DisplayName("게시물_변경_성공")
        void 게시물_변경_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute("session", avatar.getId());
            String url = "/post";
            String content = objectMapper.writeValueAsString(new UpdatePostRequest(
                    post.getId(), "change_title", "change_content"
            ));

            // when && then
            mockMvc.perform(patch(url)
                            .session(session)
                            .content(content))
                    .andDo(print())
                    .andDo(document("post-patch",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("게시물_삭제")
    class 게시물_삭제{

        @Test
        @Transactional
        @DisplayName("게시물_삭제_성공")
        void 게시물_삭제_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute("session", avatar.getId());
            String url = "/post?postID=" + post.getId();

            // when && then
            mockMvc.perform(delete(url)
                            .session(session))
                    .andDo(print())
                    .andDo(document("post-delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}