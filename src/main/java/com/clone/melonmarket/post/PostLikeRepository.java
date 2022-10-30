package com.clone.melonmarket.post;

import com.clone.melonmarket.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Boolean existsByPostAndAccount(Post post, Account account);
    void deleteByPostAndAccount(Post post, Account account);
}
