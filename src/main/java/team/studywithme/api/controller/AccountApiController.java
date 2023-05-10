package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.response.KakaoLogoutResponse;
import team.studywithme.api.controller.dto.response.UserResponse;
import team.studywithme.config.session.LoginAvatarId;
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
    public ResponseEntity studyLogin(@RequestParam String code, HttpSession httpSession){
        Long avatarID = accountService.kakaoLogin(code);
        sessionUtils.createSession(avatarID, httpSession);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/logout")
    public ResponseEntity<KakaoLogoutResponse> logout(HttpSession session){

        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.ok(new KakaoLogoutResponse());
    }

    @GetMapping("/account/get")
    public ResponseEntity<UserResponse> get(@LoginAvatarId Long avatarId){
        UserResponse userResponse = accountService.get(avatarId);

        return ResponseEntity.ok(userResponse);
    }


    @DeleteMapping("/account/delete")
    public ResponseEntity delete(@LoginAvatarId Long avatarId){

        accountService.delete(avatarId);

        return ResponseEntity.ok(null);
    }
}
