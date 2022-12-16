package com.example.appnews.controller;

import com.example.appnews.entity.Comment;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.CommentDto;
import com.example.appnews.serivice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_COMMENT')")
    @PutMapping("/edit/{id}")
    private ResponseEntity<?> edit(CommentDto commentDto, @PathVariable Long id) {
        ApiResponse apiResponse = commentService.editMyComment(commentDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 205 : 400).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT','DELETE_MY_COMMENT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 401).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteSelectAll/{id}")
    public ResponseEntity<?> deletSelactAll(@PathVariable List<Long> id){
        ApiResponse apiResponse = commentService.deleteSelectAll(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
    @GetMapping
    public List<Comment> getAll(){
        return commentService.getAll();
    }
}