package team.studywithme.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.Response.KakaoLoginResponse;
import team.studywithme.config.jwt.JwtProperties;
import team.studywithme.service.AccountService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<KakaoLoginResponse> studyLogin(@RequestParam String code, HttpSession httpSession){
        KakaoLoginResponse kakaoLoginResponse = accountService.kakaoLogin(code);
        return ResponseEntity.ok(kakaoLoginResponse);
    }
}
