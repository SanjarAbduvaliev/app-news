package com.example.appnews.controller;

import com.example.appnews.entity.Post;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.PostDto;
import com.example.appnews.serivice.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postSerivce;

    @PreAuthorize(value = "hasAnyAuthority('ADD_POST')")
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto){
        ApiResponse apiResponse = postSerivce.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody PostDto postDto,@PathVariable Long id){
        ApiResponse apiResponse = postSerivce.editPost(postDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @GetMapping("/{id}")
    public Post getId(@PathVariable Long id){
        return postSerivce.getId(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_POST')")
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = postSerivce.deletePost(id);
        return ResponseEntity .status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }

}
