package team.studywithme.api.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.config.session.SessionProperties;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.service.AccountService;
import team.studywithme.structure.UserDataTest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
class AccountApiControllerTest extends UserDataTest {

    private MockHttpSession session;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("[ACCOUNT] 로그인")
    class 로그인{

        @Test
        @Transactional
        @DisplayName("[ACCOUNT] 로그인 성공 테스트")
        void 로그인_성공_테스트() throws Exception{
            // given
            String url = "/kakao?code=24116161616";

            // mocking
            BDDMockito.given(accountService.kakaoLogin(any()))
                            .willReturn(any());

            // when && then
            mockMvc.perform(get(url))
                    .andDo(document("kakao",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[ACCOUNT] 로그아웃")
    class 로그아웃{

        @Test
        @Transactional
        @DisplayName("[ACCOUNT] 로그아웃 성공 테스트")
        void 로그아웃_성공_테스트() throws Exception{
            // given
            String url = "/logout";

            // when && then
            mockMvc.perform(get(url))
                    .andDo(document("logout",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[ACCOUNT] 회원_정보조회")
    class 회원_정보조회{

        @Test
        @Transactional
        @DisplayName("[ACCOUNT] 회원 정보조회 성공 테스트")
        void 회원_정보조회_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            String url = "/account/get";

            // when && then
            session.setAttribute(SessionProperties.SESSION, avatar.getId());

            mockMvc.perform(get(url)
                            .session(session))
                    .andDo(document("account/get",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("[ACCOUNT] 회원 정보삭제")
    class 회원_정보삭제{

        @Test
        @Transactional
        @DisplayName("[ACCOUNT] 회원 정보삭제 성공 테스트")
        void 회원_정보삭제_성공_테스트() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            String url = "/account/delete";
            session.setAttribute(SessionProperties.SESSION, avatar.getId());

            // when && then
            ResultActions result = mockMvc.perform(delete(url)
                            .session(session))
                    .andDo(document("account/delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}