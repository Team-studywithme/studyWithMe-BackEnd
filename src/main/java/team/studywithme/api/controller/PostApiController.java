package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.PostService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/post")
    public ResponseEntity<PostDetailResponse> readPost(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "postID") Long postID){

        PostDetailResponse postDetailResponse = postService.detailPost(page, postID);
        return ResponseEntity.status(HttpStatus.OK).body(postDetailResponse);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest,
                                        @LoginAvatarId Long avatarID){
        System.out.println("avatarID : " + avatarID);
        postService.createPost(postRequest, avatarID);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/post")
    public ResponseEntity<?> updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest,
                                        @LoginAvatarId Long avatarID){

        System.out.println("avatarID : " + avatarID);
        postService.updatePost(updatePostRequest, avatarID);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam Long postID,
                                        @LoginAvatarId Long avatarID){

        postService.deletePost(postID, avatarID);
        return ResponseEntity.ok(null);

    }
}
