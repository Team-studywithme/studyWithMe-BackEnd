package team.studywithme.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.config.jwt.JwtProperties;
import team.studywithme.service.AccountService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> studyLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String accessToken = accountService.getAccessToken(code);
        System.out.println("accessToken = " + accessToken);

        String userInfo = accountService.getUserInfo(accessToken);
        System.out.println("userInfo = " + userInfo);

        String token = accountService.kakaoLogic(userInfo);
        System.out.println("token = " + token);

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        return new ResponseEntity(HttpStatus.OK);
    }
}
