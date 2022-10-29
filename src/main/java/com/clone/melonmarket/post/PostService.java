package com.clone.melonmarket.post;


import com.clone.melonmarket.config.UserDetailsImpl;
import com.clone.melonmarket.global.GlobalResponseDto;
import com.clone.melonmarket.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
    public GlobalResponseDto createPost(List<MultipartFile> multipartFile,
                                        PostRequestDto postRequestDto,
                                        UserDetailsImpl userDetails) throws IOException {

        Post post = new Post(postRequestDto, userDetails.getAccount());
        postRepository.save(post);

        for (MultipartFile file : multipartFile) {
            String img = s3Uploader.uploadFiles(file, "testdir");
            System.out.println("img = " + img);
            Image image = new Image(img, post);
            imageRepository.save(image);
        }

        return new GlobalResponseDto("Success Post", HttpStatus.OK.value());
    }
}
