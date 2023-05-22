package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.structure.BoardDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BoardApiControllerTest extends BoardDataTest {

    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("게시판_조회")
    class 게시판_조회{

        @Test
        @Transactional
        @DisplayName("게시판_조회_성공")
        void 게시판_조회_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);

            String url = "/board";

            // when && then
            mockMvc.perform(get(url))
                    .andDo(print())
                    .andDo(document("board",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("내_게시판_조회")
    class 내_게시판_조회{

        @Test
        @Transactional
        @DisplayName("내_게시판_조회_성공")
        void 내_게시판_조회_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);

            session.setAttribute("session", avatar.getId());
            String url = "/my_board";

            // when && then
            mockMvc.perform(get(url)
                            .session(session))
                    .andDo(print())
                    .andDo(document("my_board",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("키워드_게시판_조회")
    class 키워드_게시판_조회{

        @Test
        @Transactional
        @DisplayName("키워드_게시판_조회_성공")
        void 키워드_게시판_조회_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);

            String keyword = "A";


            String url = "/search_board?keyword=" + keyword;

            // when && then
            mockMvc.perform(get(url))
                    .andDo(print())
                    .andDo(document("search_board",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}