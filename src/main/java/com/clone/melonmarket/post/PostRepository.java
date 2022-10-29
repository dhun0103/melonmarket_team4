package com.clone.melonmarket.post;

import com.clone.melonmarket.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByAccount(Account account);
}
