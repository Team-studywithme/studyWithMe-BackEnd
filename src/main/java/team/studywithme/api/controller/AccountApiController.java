package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.response.KakaoLoginResponse;
import team.studywithme.config.session.Session;
import team.studywithme.service.AccountService;
import team.studywithme.utils.session.SessionUtils;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;
    private final SessionUtils sessionUtils;

    @GetMapping("/kakao")
    public ResponseEntity<KakaoLoginResponse> studyLogin(@RequestParam String code, HttpSession httpSession){
        KakaoLoginResponse kakaoLoginResponse = accountService.kakaoLogin(code);
        sessionUtils.createSession(kakaoLoginResponse, httpSession);

        return ResponseEntity.ok(kakaoLoginResponse);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@Session HttpSession session){
        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
