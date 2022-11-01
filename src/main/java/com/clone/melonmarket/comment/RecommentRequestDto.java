package com.clone.melonmarket.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommentRequestDto extends CommentRequestDto{

    private Long commentId;

}
