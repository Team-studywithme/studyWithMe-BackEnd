package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.api.controller.dto.request.NicknameRequest;
import team.studywithme.config.session.SessionProperties;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.structure.UserDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class})
class AvatarApiControllerTest extends UserDataTest {
    private MockHttpSession session;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("[AVATAR] 회원 정보 수정")
    class 회원_정보수정{

        @Test
        @Transactional
        @DisplayName("[AVATAR] 회원 정보수정 성공 테스트")
        void 회원_정보수정_성공_테스트() throws Exception{
            // given
            String content = objectMapper.writeValueAsString(new NicknameRequest("닉네임_변경후"));

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            session.setAttribute(SessionProperties.SESSION, avatar.getId());
            String url = "/avatar/update";

            // when && then
            mockMvc.perform(patch(url)
                            .session(session)
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(document("avatar/update" ,
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                            fieldWithPath("nickname").description("변경시킬 유저의 닉네임"))
                    ))
                    .andExpect(status().isOk());
        }
    }
}