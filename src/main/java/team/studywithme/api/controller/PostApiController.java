package team.studywithme.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.studywithme.api.controller.dto.request.PostRequest;
import team.studywithme.api.controller.dto.request.UpdatePostRequest;
import team.studywithme.api.controller.dto.response.PostDetailResponse;
import team.studywithme.config.session.LoginAvatarId;
import team.studywithme.service.PostService;


@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/post")
    public ResponseEntity<PostDetailResponse> readPost(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                                       @RequestParam(value = "postID") Long postID){

        PostDetailResponse postDetailResponse = postService.detailPost(pageable, postID);
        return ResponseEntity.status(HttpStatus.OK).body(postDetailResponse);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest,
                                        @LoginAvatarId Long avatarID){

        postService.createPost(postRequest, avatarID);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody UpdatePostRequest updatePostRequest,
                                        @LoginAvatarId Long avatarID){

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
