package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.GenericSingleRequest;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.AvatarService;

@RestController
@RequiredArgsConstructor
public class AvatarApiController {

    private final AvatarService avatarService;


    @PostMapping("/avatar/update")
    public ResponseEntity<?> update(@RequestBody GenericSingleRequest<String> genericSingleRequest, @LoginAvatarId Long avatarId){

        int result = avatarService.update(avatarId, genericSingleRequest.getElement());

        return (result != 0) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}