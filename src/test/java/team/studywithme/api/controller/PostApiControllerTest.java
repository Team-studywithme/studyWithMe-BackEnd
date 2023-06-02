package team.studywithme.api.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.config.session.SessionProperties;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith({RestDocumentationExtension.class})
class PostApiControllerTest extends PostDataTest {

    private MockHttpSession session;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
        session = new MockHttpSession();
    }

    @AfterEach
    public void deleteRepository(){
        deleteAllRepository();
    }

    @Nested
    @DisplayName("[POST] 게시물 조회")
    class 게시물_조회{

        @Test
        @DisplayName("[POST] 게시물 조회 성공 테스트")
        void 게시물_조회_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);
            makeCommentList(avatar, post);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/post?postID=" + post.getId();

            // when && then
            mockMvc.perform(get(url)
                            .session(session))
                    .andDo(document("post-get",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestParameters(
                                    parameterWithName("postID").description("조회 할 게시물 PK")
                            ),
                            responseFields(
                                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시물 PK"),
                                    fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                                    fieldWithPath("createdDate").type(JsonFieldType.STRING).description("게시물 생성시간"),
                                    fieldWithPath("avatarID").type(JsonFieldType.NUMBER).description("게시물 작성자 PK"),
                                    fieldWithPath("nickname").type(JsonFieldType.STRING).description("게시물 작성자 닉네임"),
                                    fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                                    fieldWithPath("hits").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                    fieldWithPath("next").type(JsonFieldType.BOOLEAN).description("다음 게시물 페이지의 여부"),

                                    fieldWithPath("commentDetailResponseList[].id").type(JsonFieldType.NUMBER).description("게시물에 달린 댓글 PK"),
                                    fieldWithPath("commentDetailResponseList[].content").type(JsonFieldType.STRING).description("게시물에 달린 댓글 내용"),
                                    fieldWithPath("commentDetailResponseList[].avatarID").type(JsonFieldType.NUMBER).description("게시물에 달린 댓글 작성자 PK"),
                                    fieldWithPath("commentDetailResponseList[].nickname").type(JsonFieldType.STRING).description("게시물에 달린 댓글 작성자 닉네임")

                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[POST] 게시물 생성")
    class 게시물_생성{

        @Test
        @DisplayName("[POST] 게시물 생성 성공 테스트")
        void 게시물_생성_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/post";
            String content = objectMapper.writeValueAsString(new PostRequest(
                    board.getId(), post.getTitle(), post.getContent()
            ));

            // when && then
            mockMvc.perform(post(url)
                            .session(session)
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("post-post",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                                    fieldWithPath("board_id").description("생성할 게시판 PK"),
                                    fieldWithPath("title").description("생성할 게시판 제목"),
                                    fieldWithPath("content").description("생성할 게시판 내용")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[POST] 게시물 변경")
    class 게시물_변경{

        @Test
        @DisplayName("[POST] 게시물 변경 성공 테스트")
        void 게시물_변경_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/post";
            String content = objectMapper.writeValueAsString(new UpdatePostRequest(
                    post.getId(), "change_title", "change_content"
            ));

            // when && then
            mockMvc.perform(patch(url)
                            .session(session)
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("post-patch",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                                    fieldWithPath("post_id").description("변경할 게시물 PK"),
                                    fieldWithPath("title").description("변경할 게시물 제목"),
                                    fieldWithPath("content").description("변경할 게시물 내용")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[POST] 게시물 삭제")
    class 게시물_삭제{

        @Test
        @DisplayName("[POST] 게시물 삭제 성공 테스트")
        void 게시물_삭제_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            Post post = makePost(avatar, board);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/post?postID=" + post.getId();

            // when && then
            mockMvc.perform(delete(url)
                            .session(session))
                    .andDo(document("post-delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestParameters(
                                    parameterWithName("postID").description("삭제 할 게시물 PK")
                            )))
                    .andExpect(status().isOk());
        }
    }
}