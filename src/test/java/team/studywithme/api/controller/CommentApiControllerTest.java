package team.studywithme.api.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.domain.entity.Board;
import team.studywithme.domain.entity.Comment;
import team.studywithme.domain.entity.Post;
import team.studywithme.structure.PostDataTest;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith({RestDocumentationExtension.class})
class CommentApiControllerTest extends PostDataTest {

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
    @DisplayName("[Comment] 댓글 생성")
    class 댓글_생성{

        @Test
        @DisplayName("[Comment] 댓글 생성 성공 테스트")
        void 댓글_생성_성공_테스트() throws Exception{
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
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("comment-post",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                                    fieldWithPath("post_id").description("댓글을 달 게시물 PK"),
                                    fieldWithPath("content").description("생성할 댓글 내용")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[Comment] 댓글_수정")
    class 댓글_수정{

        @Test
        @DisplayName("[Comment] 댓글_수정_성공 테스트")
        void 댓글_수정_성공_테스트() throws Exception{
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
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("comment-patch",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                                    fieldWithPath("comment_id").description("변경할 댓글 PK"),
                                    fieldWithPath("content").description("변경할 댓글 내용")
                            )))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[Comment] 댓글 삭제")
    class 댓글_삭제{

        @Test
        @DisplayName("[Comment] 댓글 삭제 성공 테스트")
        void 댓글_삭제_성공_테스트() throws Exception{
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
                    .andDo(document("comment-delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestParameters(
                                    parameterWithName("commentID").description("삭제 할 댓글 PK")
                            )))
                    .andExpect(status().isOk());
        }
    }
}