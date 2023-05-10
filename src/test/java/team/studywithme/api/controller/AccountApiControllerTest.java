package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.service.AccountService;
import team.studywithme.structure.UserDataTest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class AccountApiControllerTest extends UserDataTest {

    private MockHttpSession session;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("로그인")
    class 로그인{

        @Test
        @Transactional
        @DisplayName("로그인_성공")
        void 로그인_성공() throws Exception{
            // given
            String url = "/kakao?code=24116161616";

            // mocking
            BDDMockito.given(accountService.kakaoLogin(any()))
                            .willReturn(any());

            // when && then
            mockMvc.perform(get(url))
                    .andDo(print())
                    .andDo(document("kakao",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("로그아웃")
    class 로그아웃{

        @Test
        @Transactional
        @DisplayName("로그아웃_성공")
        void 로그아웃_성공() throws Exception{
            // given
            String url = "/logout";

            // when && then
            mockMvc.perform(get(url))
                    .andDo(print())
                    .andDo(document("logout",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("회원_정보삭제")
    class 회원_정보삭제{

        @Test
        @Transactional
        @DisplayName("회원_정보삭제_성공")
        void 회원_정보수정_성공() throws Exception{
            // given
            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            session.setAttribute("session", avatar.getId());
            String url = "/account/delete";

            // when && then
            mockMvc.perform(delete(url)
                            .session(session))
                    .andDo(print())
                    .andDo(document("account/delete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}