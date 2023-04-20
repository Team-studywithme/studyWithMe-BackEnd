package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.studywithme.api.controller.dto.request.GenericSingleRequest;
import team.studywithme.api.controller.dto.response.KakaoLoginResponse;
import team.studywithme.config.session.Session;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.service.AvatarService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AvatarApiController {

    private final AvatarService avatarService;

    /** 리졸버 로직관련
     * request.getSession(false); => 세션이 있으면 session 없으면 null
     *  request.getSession(); => 세션이 있으면 session 없으면 세션을 만들어서 줌
     *
     * 무엇을 하던간에 if(session == null) 또는 if(session.getAttribute("session") == null) 이라는 조건이 무조건 붙어
     * 그래서 true로 하면 괜히 세션 만들어주니까 false 처리
     * */

    @PatchMapping("/avatar/update")
    public ResponseEntity<Avatar> update(@RequestBody GenericSingleRequest<String> genericSingleRequest, @Session HttpSession session){
        if(session == null){ return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }

        KakaoLoginResponse kakaoLoginResponse = (KakaoLoginResponse) session.getAttribute("session");
        Avatar avatar = avatarService.update(kakaoLoginResponse.getAvatarID(), genericSingleRequest.getElement());

        return (avatar != null) ?
                ResponseEntity.status(HttpStatus.OK).body(avatar) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/avatar/delete")
    public ResponseEntity<?> delete(@Session HttpSession session){
        if(session == null){ return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }

        KakaoLoginResponse kakaoLoginResponse = (KakaoLoginResponse) session.getAttribute("session");
        avatarService.delete(kakaoLoginResponse.getAvatarID());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}