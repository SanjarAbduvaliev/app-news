package com.example.appnews.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CommentDto {
    @Size(max = 100)
    private String text;
    private Long postId;
    private  List<Long> id;
}
