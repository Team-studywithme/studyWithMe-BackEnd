package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.NicknameRequest;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.AvatarService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AvatarApiController {

    private final AvatarService avatarService;


    @PatchMapping("/avatar/update")
    public ResponseEntity<?> update(@Valid @RequestBody NicknameRequest nicknameRequest,
                                    @LoginAvatarId final Long avatarId){

        int result = avatarService.update(avatarId, nicknameRequest.getNickname());

        return (result != 0) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}