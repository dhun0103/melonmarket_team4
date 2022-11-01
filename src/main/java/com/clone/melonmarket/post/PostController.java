package com.clone.melonmarket.post;


import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성하기
    // 타입 형식을 지정해주면 에러를 줄일 수 있음
    @PostMapping(value = "/posts", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public GlobalResponseDto createPost(@RequestParam(value = "post", required = false) String post,
//                                        @RequestPart(value = "image", required = false) List<MultipartFile> image,
                                        MultipartHttpServletRequest multipartHttpServletRequest,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        Gson gson = new Gson();
        PostRequestDto postRequestDto = gson.fromJson(post,PostRequestDto.class);

       List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("images");


        return postService.createPost(multipartFiles, postRequestDto, userDetails);
    }

    // 게시글 수정하기
    @PostMapping(value = "/posts/{postId}")
    public GlobalResponseDto updatePost(@RequestParam("post") String post,
                                    @RequestPart("image") List<MultipartFile> image,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @PathVariable Long postId) throws IOException {

        Gson gson = new Gson();
        PostRequestDto postRequestDto = gson.fromJson(post,PostRequestDto.class);

        return postService.updatePost(image, postRequestDto, userDetails, postId);
    }

    // 삭제하기
    @DeleteMapping(value = "/posts/{postId}")
    public GlobalResponseDto deletePost(@PathVariable Long postId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.deletePost(postId, userDetails);
    }

    // 하나 조회
    @GetMapping(value = "/posts/{postId}")
    public PostResponseDto getOnePost(@PathVariable Long postId) {
        return postService.getOnePost(postId);
    }

    // 전체 조회
    @GetMapping(value = "/posts")
    public List<PostAllResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    // 판매 완료
    @PostMapping(value = "/sale/{postId}")
    public GlobalResponseDto postSale(@PathVariable Long postId,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.postSale(postId, userDetails);
    }




}
