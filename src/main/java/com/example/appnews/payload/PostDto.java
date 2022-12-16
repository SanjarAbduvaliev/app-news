package com.example.appnews.payload;

import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String text;
    private String url;
}
