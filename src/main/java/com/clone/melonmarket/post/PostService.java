package com.clone.melonmarket.post;


import com.clone.melonmarket.account.Account;
import com.clone.melonmarket.comment.Comment;
import com.clone.melonmarket.comment.CommentResponseDto;
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
import java.util.ArrayList;
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
        //예외처리


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

    @Transactional
    public GlobalResponseDto deletePost(Long postId, UserDetailsImpl userDetails) {

        // postId로 Post객체 찾기.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NotFoundPost));

        // 게시글 작성자만 삭제 하도록 예외처리
        if (!userDetails.getAccount().getAccountId().equals(post.getAccount().getAccountId()))
            throw new CustomException(ErrorCode.CantDelete);

        // 삭제
        postRepository.deleteById(postId);

        return new GlobalResponseDto("게시글 삭제가 완료되었습니다!", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getOnePost(Long postId) {

        // postId로 게시글 하나조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NotFoundPost));

        // 이미지 개수만큼 리스트에 추가
        List<Image> images = new ArrayList<>();
        for (Image image : post.getImage()) {
            images.add(image);
        }

        // 게시글에 해당하는 댓글 post에 추가
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : post.getComments()){
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }

        return new PostResponseDto(post, commentResponseDtoList, images);
    }

    @Transactional(readOnly = true)
    // 전체조회
    public List<PostAllResponseDto> getAllPost() {

        // 전체 게시글 가져오기
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostAllResponseDto> postAllResponseDtoList = new ArrayList<>();
        for (Post post: postList) {
            if (!post.getStatus()) postAllResponseDtoList.add(new PostAllResponseDto(post));
        }

        return postAllResponseDtoList;



    }

    // 판매완료
    @Transactional
    public GlobalResponseDto postSale(Long postId, UserDetailsImpl userDetails) {

        // 해당 게시글 정보 가져오기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NotFoundPost));

        if (!userDetails.getAccount().getAccountId().equals(post.getAccount().getAccountId()))
            throw  new CustomException(ErrorCode.CANTSALECOMPLETE);

        if (post.getStatus()) throw new CustomException(ErrorCode.ALREADYSALECOMPLETE);
        else post.updatePostStatus(true);

        return new GlobalResponseDto("판매가 완료 되었습니다.", HttpStatus.OK.value());
    }
}
