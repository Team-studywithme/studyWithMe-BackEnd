package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.Response.KakaoLoginResponse;
import team.studywithme.service.AccountService;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/kakao")
    public ResponseEntity<KakaoLoginResponse> studyLogin(@RequestParam String code, HttpSession httpSession){
        KakaoLoginResponse kakaoLoginResponse = accountService.kakaoLogin(code);
        return ResponseEntity.ok(kakaoLoginResponse);
    }
}
