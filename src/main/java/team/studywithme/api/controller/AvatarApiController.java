package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping("/avatar/update")
    public ResponseEntity<Avatar> update(@RequestBody GenericSingleRequest<String> genericSingleRequest, HttpSession session){
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