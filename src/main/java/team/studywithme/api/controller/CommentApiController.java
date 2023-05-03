package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.CommentRequest;
import team.studywithme.api.controller.dto.request.UpdateCommentRequest;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest,
                                           @LoginAvatarId Long avatarID){

        commentService.createComment(commentRequest, avatarID);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest,
                                           @LoginAvatarId Long avatarID){

        commentService.updateComment(updateCommentRequest, avatarID);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam Long commentID,
                                           @LoginAvatarId Long avatarID){

        commentService.deleteComment(commentID, avatarID);
        return ResponseEntity.ok(null);
    }
}
