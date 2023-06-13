package team.studywithme.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import team.studywithme.api.controller.dto.response.MyTimerResponse;
import team.studywithme.api.controller.dto.response.StudyingAvatarListResponse;
import team.studywithme.api.controller.dto.response.StudyingUserResponse;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.service.StudyTimeService;
import team.studywithme.service.TimerService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class})
@ActiveProfiles(profiles = "local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.kakao.id=qqweqweasfasgasgas")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class TimerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    TimerService timerService;

    @MockBean
    StudyTimeService studyTimeService;

    MockHttpSession session = new MockHttpSession();


    Avatar avatar;

    @BeforeEach
    public void init(WebApplicationContext context, RestDocumentationContextProvider restDocumentationContextProvider) {
        avatar = new Avatar("hihi");
        session.setAttribute("session", avatar.getId());
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(documentationConfiguration(restDocumentationContextProvider)).build();
    }


    @Test
    @DisplayName("[Docs][API]타이머 시작 기능 적용")
    public void startTimerTest() throws Exception {
        given(timerService.saveMyTimerStartTime(eq(avatar.getId()))).willReturn(new MyTimerResponse(any(LocalTime.class)));

        mockMvc.perform(
                        get("/api/timer/startTimer")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("timerstart",
                        responseFields(fieldWithPath("studyTime")
                                .description("오늘까지 총 공부한 시간 만약 오늘 처음 요청을 보내는 거면  00:00:00으로 출력함"))));
    }

    @Test
    @DisplayName("[Docs][API]타이머 종료 기능 적용")
    public void endTimerTest() throws Exception {
        given(timerService.saveMyTimerEndTime(eq(avatar.getId()))).willReturn(new MyTimerResponse(any(LocalTime.class)));

        mockMvc.perform(get("/api/timer/quitTimer")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(document("timerend",
                        responseFields(fieldWithPath("studyTime")
                                .description("오늘까지 총 공부한 시간 00:00:00으로 출력함"))));
    }

    @Test
    @DisplayName("[API]공부중인 유저 리스트 불러오기")
    void studyTimerList() throws Exception{
        mockMvc.perform(get("/api/timer/studyingUserList")
                .session(session))
                .andExpect(status().isOk());
    }

}