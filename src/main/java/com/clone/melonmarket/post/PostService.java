package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.exception.CustomException;
import com.clone.melonmarket.exception.ErrorCode;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.clone.melonmarket.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final S3Uploader s3Uploader;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    // 게시글 작성
    @Transactional
    public GlobalResponseDto createPost(List<MultipartFile> multipartFile,
                                        PostRequestDto postRequestDto,
                                        UserDetailsImpl userDetails) throws IOException {

        // 유저 정보로 Post객체 생성
        Post post = new Post(postRequestDto, userDetails.getAccount());
        postRepository.save(post);

        // List로 image받은후 저장
        for (MultipartFile file : multipartFile) {
            String img = s3Uploader.uploadFiles(file, "testdir");
            System.out.println("img = " + img);
            Image image = new Image(img, post);
            imageRepository.save(image);
        }

        return new GlobalResponseDto("Success Post", HttpStatus.OK.value());
    }


    // 업데이트
    @Transactional
    public GlobalResponseDto updatePost(List<MultipartFile> multipartFile,
                                        PostRequestDto postRequestDto,
                                        UserDetailsImpl userDetails,
                                        Long postId) throws IOException {
        // 유저 객체
        Account account = userDetails.getAccount();

        // Post 객체 받아오기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NotFoundPost));

        // 작성자만 삭제할수 있게 예외처리
        if (!account.getAccountId().equals(post.getAccount().getAccountId()))
             throw new CustomException(ErrorCode.NOTMATCHEDACCOUNT);

        // 게시글 수정
        post.updatePost(postRequestDto);

        // 기존에 있는 Image삭제
        List<Image> image = imageRepository.findByPostPostId(postId);
        for (int i=0;i<image.size();i++){
            imageRepository.deleteByPostPostId(postId);
        }

        // 받아온 이미지 저장
        for (MultipartFile file : multipartFile) {
            String img = s3Uploader.uploadFiles(file, "testdir");
            Image newImage = new Image(img, post);
            imageRepository.save(newImage);
        }

        return new GlobalResponseDto("Success Update", HttpStatus.OK.value());
    }
}
