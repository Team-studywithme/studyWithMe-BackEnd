package team.studywithme.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import team.studywithme.api.controller.dto.request.NicknameRequest;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.structure.UserDataTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AvatarApiControllerTest extends UserDataTest {
    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
    }

    @Nested
    @DisplayName("회원_정보수정")
    class 회원_정보수정{

        @Test
        @Transactional
        @DisplayName("회원_정보수정_성공")
        void 회원_정보수정_성공() throws Exception{
            // given
            String content = objectMapper.writeValueAsString(new NicknameRequest("닉네임_변경후"));

            Avatar avatar = makeAvatar();
            makeAccount(avatar);

            session.setAttribute("session", avatar.getId());
            String url = "/avatar/update";

            // when && then
            mockMvc.perform(post(url)
                            .session(session)
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andDo(document("avatar/update",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
                    .andExpect(status().isOk());
        }
    }
}