package com.clone.melonmarket.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPostPostId(Long postId);
    void deleteByPostPostId(Long postId);

}
