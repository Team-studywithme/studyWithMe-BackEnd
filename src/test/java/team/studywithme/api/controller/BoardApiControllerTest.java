package team.studywithme.api.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.config.session.SessionProperties;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.structure.BoardDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith({RestDocumentationExtension.class})
class BoardApiControllerTest extends BoardDataTest {

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
    @DisplayName("[BOARD] 게시판 조회")
    class 게시판_조회{

        @Test
        @DisplayName("[BOARD] 게시판 조회 성공 테스트")
        void 게시판_조회_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);


            String url = "/board";

            // when && then
            mockMvc.perform(get(url))
                    .andDo(document("board-get",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            responseFields(
                                    fieldWithPath("name").type(JsonFieldType.STRING).description("게시판의 이름"),

                                    fieldWithPath("postResponseList[].id").type(JsonFieldType.NUMBER).description("게시물 PK"),
                                    fieldWithPath("postResponseList[].title").type(JsonFieldType.STRING).description("게시물 제목"),
                                    fieldWithPath("postResponseList[].hits").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                    fieldWithPath("postResponseList[].createdDate").type(JsonFieldType.STRING).description("게시물 생성일자"),
                                    fieldWithPath("postResponseList[].avatarID").type(JsonFieldType.NUMBER).description("게시물 작성자 PK"),
                                    fieldWithPath("postResponseList[].nickname").type(JsonFieldType.STRING).description("게시물 작성자 닉네임")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[BOARD] 내 게시판 조회")
    class 내_게시판_조회{

        @Test
        @DisplayName("[BOARD] 내 게시판 조회 성공 테스트")
        void 내_게시판_조회_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/my_board";

            // when && then
            mockMvc.perform(get(url)
                            .session(session))
                    .andDo(document("my_board",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            responseFields(
                                    fieldWithPath("name").type(JsonFieldType.STRING).description("게시판의 이름"),

                                    fieldWithPath("postResponseList[].id").type(JsonFieldType.NUMBER).description("게시물 PK"),
                                    fieldWithPath("postResponseList[].title").type(JsonFieldType.STRING).description("게시물 제목"),
                                    fieldWithPath("postResponseList[].hits").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                    fieldWithPath("postResponseList[].createdDate").type(JsonFieldType.STRING).description("게시물 생성일자"),
                                    fieldWithPath("postResponseList[].avatarID").type(JsonFieldType.NUMBER).description("게시물 작성자 PK"),
                                    fieldWithPath("postResponseList[].nickname").type(JsonFieldType.STRING).description("게시물 작성자 닉네임")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[BOARD] 검색 게시판 조회")
    class 검색_게시판_조회{

        @Test
        @DisplayName("[BOARD] 검색 게시판 조회 성공 테스트")
        void 검색_게시판_조회_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            Board board = makeBoard();
            makePostList(avatar, board);

            String keyword = "title";

            String url = "/search_board?keyword=" + keyword;

            // when && then
            mockMvc.perform(get(url))
                    .andDo(document("search_board",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestParameters(
                                    parameterWithName("keyword").description("검색할 키워드")
                            ),
                            responseFields(
                                    fieldWithPath("name").type(JsonFieldType.STRING).description("게시판의 이름"),

                                    fieldWithPath("postResponseList[].id").type(JsonFieldType.NUMBER).description("게시물 PK"),
                                    fieldWithPath("postResponseList[].title").type(JsonFieldType.STRING).description("게시물 제목"),
                                    fieldWithPath("postResponseList[].hits").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                    fieldWithPath("postResponseList[].createdDate").type(JsonFieldType.STRING).description("게시물 생성일자"),
                                    fieldWithPath("postResponseList[].avatarID").type(JsonFieldType.NUMBER).description("게시물 작성자 PK"),
                                    fieldWithPath("postResponseList[].nickname").type(JsonFieldType.STRING).description("게시물 작성자 닉네임")
                            )))
                    .andExpect(status().isOk());
        }
    }
}